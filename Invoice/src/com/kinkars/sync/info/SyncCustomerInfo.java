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
import org.apache.log4j.Logger;

public class SyncCustomerInfo {
	final static Logger logger = Logger.getLogger(SyncCustomerInfo.class);
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
}


