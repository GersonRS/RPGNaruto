package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static String url = "jdbc:mysql://localhost/mydb";
	private static String userName = "root";
	private static String password = "1234";
	private static String driver = "com.mysql.jdbc.Driver";

	private static Connection connection;

	public synchronized static Connection getConexao() {
		if (connection != null) {
			return connection;
		} else {
				try {
					Class.forName(driver).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			try {
				connection = DriverManager.getConnection(url, userName, password);
				return connection;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		}
	}

}
