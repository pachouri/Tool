package com.kinkars.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.kinkars.database.sqlserver.ConnectMSSQLServer;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.bean.FamilyInfo;
import com.kinkars.sync.info.sqlquery.InvoiceSQLQuery;

public class CommonUtility {
	final static Logger logger = Logger.getLogger(CommonUtility.class);
	public  int getNexValue(String dfinfo, String table, String field){

		GetPropertyValues prop = new GetPropertyValues();
		List<FamilyInfo> fi=new ArrayList<FamilyInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		int nextnumber=0;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getNextNubmer(prop.getPropValues().getProperty("Database")+"."+table,field);
			ResultSet rs = statement.executeQuery(queryString);

			if (rs.next()) {
				nextnumber=rs.getInt(1);
			} 

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


		return nextnumber;
	}

	public String getVCHNOPadded(String vchno){
		int result=25;
		return String.format("%"+result+"s", vchno);

	}
	public static void main(String [] args){
		CommonUtility cu=new CommonUtility();
		
		System.out.println(cu.getClientMasterCode(2));

	}

	public  int getNextRecordToBeMigrate(int mode){
		GetPropertyValues prop = new GetPropertyValues();
		String  result=null;
		int nextRecord=0;
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = null;
			if( mode == 1){
				queryString = InvoiceSQLQuery.getNextRecordToBeMigrateInvoice(prop.getPropValues().getProperty("Database"));
			}
			if( mode == 2){
				queryString = InvoiceSQLQuery.getNextRecordToBeMigratePayment(prop.getPropValues().getProperty("Database"));
			}
			System.out.println(queryString);
			ResultSet rs = statement.executeQuery(queryString);

			if (rs.next()) {
				result=rs.getString(1).trim();
				System.out.println("Result"+result);
				//result ="IP-1-0";
				String  type=null;
				if( mode == 1){
					type=prop.getPropValues().getProperty("INOVICENAMEPREFIX");
				}
				if(mode == 2){
					type=prop.getPropValues().getProperty("PAYMENTNAMEPREFIX");
				}

				if(result.startsWith(type)){
					result = result.substring(result.indexOf("-") + 1);
					result= result.substring(0, result.indexOf("-"));
					nextRecord=(Integer.parseInt(result))+1;
				}else{
					nextRecord=1;
				}

			} else{
				nextRecord=1;
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


		return nextRecord;
	}

	public  int getClientMasterCode(int invoiceid){
		GetPropertyValues prop = new GetPropertyValues();
		String  result=null;
		int mastercode1=0;
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getClientID(prop.getPropValues().getProperty("Database"));
			System.out.println(queryString);
			ResultSet rs = statement.executeQuery(queryString);

			while(rs.next()) {
				result=rs.getString(1).trim();
				System.out.println("Result"+result);
				String type=prop.getPropValues().getProperty("INOVICENAMEPREFIX");
				if(result.startsWith(type)){
					result = result.substring(result.indexOf("-") + 1);
					result= result.substring(0, result.indexOf("-"));
					Integer.parseInt(result);
					if(Integer.parseInt(result) == invoiceid){
						mastercode1 =rs.getInt(2);
					}
				}
			} 

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


		return mastercode1;
	}
}
