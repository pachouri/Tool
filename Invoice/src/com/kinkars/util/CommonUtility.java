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
		int result=25;System.out.println(result);
		return String.format("%"+result+"s", vchno);
		
	}
	public static void main(String [] args){
		CommonUtility cu=new CommonUtility();
		System.out.println(cu.getNexValue("BusyComp0001_db12018.dbo","tran1","vchcode"));

	}

}
