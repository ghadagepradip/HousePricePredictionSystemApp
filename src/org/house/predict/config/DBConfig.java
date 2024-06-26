package org.house.predict.config;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConfig {

	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;

	private static DBConfig db=null;
	private DBConfig() {
		try {
			Properties p = new Properties();
			p.load(PathHelper.fin);
			String driverClassName = p.getProperty("driver.classname");
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			String url = p.getProperty("db.url");
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			if (conn != null) {
				System.out.println("Database is connected");
			} else {
				System.out.println("Database is not conneted");
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
	}
	public static DBConfig getDBInstance()
	{
		if(db==null)
		{
			db= new DBConfig();
		}
		return db;
	}
	public  Connection getConnection()
	{
		return conn;
	}
	public  PreparedStatement getStatement()
	{
		return stmt;
	}
	public  ResultSet getResultSet()
	{
		return rs;
	}

}
