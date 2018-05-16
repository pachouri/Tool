package com.kinkars.database.sqlserver;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class ConnectMSSQLServer
{
	final static Logger logger = Logger.getLogger(ConnectMSSQLServer.class);
	public Connection dbConnect()
	{
		Connection conn=null;
		try {
			GetPropertyValues prop = new GetPropertyValues();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(prop.getPropValues().getProperty("host"),prop.getPropValues().getProperty("username"), prop.getPropValues().getProperty("password"));
			logger.info("Database Connected");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return conn;
	}
}