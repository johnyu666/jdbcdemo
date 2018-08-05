package cn.johnyu.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {
	private static Properties conf=new Properties();
	private static BasicDataSource dataSource=new BasicDataSource();
	static {
		try {
			conf.load(new FileInputStream("dbconf_mysql.properties"));
			String url=conf.getProperty("url");
			String username=conf.getProperty("username");
			String password=conf.getProperty("password");
			String driverClass=conf.getProperty("driverClass");
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setDriverClassName(driverClass);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  Connection getConnectionWithDbcp() {		
		Connection con=null;
		try {
			con=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static  Connection getConnection() {
		String url=conf.getProperty("url");
		String username=conf.getProperty("username");
		String password=conf.getProperty("password");
		String driverClass=conf.getProperty("driverClass");
		Connection con=null;
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con,Statement st,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Connection con=DBUtil.getConnection();
		System.out.println(con);
	}
}
