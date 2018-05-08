package com.kinkars.sync.info;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kinkars.database.sqlserver.ConnectMSSQLServer;

public class SyncCustomerInfo {
	public void getSyncClientInfo(){
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement();
		

		String queryString = "select MasterAddressInfo.MasterCode, Master1.Name,MasterAddressInfo.Address1, MasterAddressInfo.Address2, MasterAddressInfo.Address3 from BusyComp0001_db12018.dbo.MasterAddressInfo  INNER JOIN  BusyComp0001_db12018.dbo.Master1 ON MasterAddressInfo.MasterCode=Master1.Code";
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
		} 
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

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


