package gr.project.future.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {

		Connection connection = null;

		try {
			FileInputStream file = new FileInputStream("db.properties");
			Properties properties = new Properties();
			properties.load(file);

			// load the Driver Class
			Class.forName(properties.getProperty("DB_DRIVER"));

			// create the connection now
			connection = DriverManager.getConnection(properties.getProperty("DB_CONNECTION"),
					properties.getProperty("DB_USER"),
					properties.getProperty("DB_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
