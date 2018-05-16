package com.kinkars.sync.info;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.kinkars.client.rest.RestInputBuilder;
import com.kinkars.database.sqlserver.ConnectMSSQLServer;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.sqlquery.InvoiceSQLQuery;
import com.kinkars.sync.info.bean.ClientInfo;
import com.kinkars.sync.info.bean.FamilyInfo;

import org.apache.log4j.Logger;

public class SyncInvoiceInfo {
	final static Logger logger = Logger.getLogger(SyncInvoiceInfo.class);

	public void getSyncClientInfo(){
		GetPropertyValues prop = new GetPropertyValues();
		List<ClientInfo> ci=new ArrayList<ClientInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getMasterAddressInfo(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				ClientInfo clientInfo=new ClientInfo();;
				clientInfo.setExt_client_id(rs.getInt("MasterCode"));
				clientInfo.setClient_name(rs.getString("Name"));
				clientInfo.setClient_address_1(rs.getString("Address1"));
				clientInfo.setClient_address_2(rs.getString("Address2")+","+ rs.getString("Address3")+","+ rs.getString("Address4"));
				clientInfo.setClient_phone(rs.getString("TelNo"));
				clientInfo.setClient_fax(rs.getString("FAX"));
				clientInfo.setClient_email(rs.getString("EMAIL"));
				clientInfo.setClient_mobile(rs.getString("Mobile"));
				clientInfo.setClient_vat_id(rs.getString("GSTNo"));
				ci.add(clientInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncClients(ci);
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
	}

	public void getSyncFamiliesInfo(){
		GetPropertyValues prop = new GetPropertyValues();
		List<FamilyInfo> fi=new ArrayList<FamilyInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getParentGroup(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				FamilyInfo familyInfo=new FamilyInfo();
				familyInfo.setExt_family_id(rs.getInt("Code"));
				familyInfo.setFamily_name(rs.getString("Name"));
				fi.add(familyInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncFamilies(fi);
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
	}
}