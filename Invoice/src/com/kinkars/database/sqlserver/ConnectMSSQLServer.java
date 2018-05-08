package com.kinkars.database.sqlserver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQLServer
{
	public Connection dbConnect()
	{
		Connection conn=null;
		try {
			GetPropertyValues prop = new GetPropertyValues();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(prop.getPropValues().getProperty("host"),prop.getPropValues().getProperty("username"), prop.getPropValues().getProperty("password"));
			System.out.println("connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}