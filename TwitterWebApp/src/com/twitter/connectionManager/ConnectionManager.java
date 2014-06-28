package com.twitter.connectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;


public class ConnectionManager {
	

	 private static Properties properties = new Properties();
	 private static InputStream inputStream = null;
	 private static Connection connection =null;
	 public static Connection getConnection(){
		inputStream = ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties");
		System.out.println(inputStream);

		 try {
			properties.load(inputStream);
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(properties.getProperty("driver"));
		basicDataSource.setUrl(properties.getProperty("database"));
		basicDataSource.setUsername(properties.getProperty("user"));
		basicDataSource.setPassword(properties.getProperty("password"));
	   
			connection = basicDataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
		
	}

}
