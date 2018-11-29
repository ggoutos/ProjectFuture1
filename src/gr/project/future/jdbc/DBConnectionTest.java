package gr.project.future.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String QUERY = "SELECT * FROM customers WHERE ContactTitle = ?";

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(QUERY);
            statement.setString(1, "Owner");
            rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("CompanyName");
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
