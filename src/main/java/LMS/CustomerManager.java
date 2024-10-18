package LMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CustomerManager {
    public void addCustomer(Customer customer) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getPhone());

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Customer added successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
