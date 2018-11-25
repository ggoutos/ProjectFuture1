package gr.project.future.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {

        String QUERY = "SELECT * FROM customers WHERE ContactTitle = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, "Owner");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("CompanyName");
                System.out.println(name);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
