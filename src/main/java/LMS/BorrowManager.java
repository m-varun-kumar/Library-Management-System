package LMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BorrowManager {
    public boolean borrowBook(int customerId, int bookId) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String checkAvailabilityQuery = "SELECT is_available FROM books WHERE book_id = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkAvailabilityQuery);
                checkStmt.setInt(1, bookId);
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next() && resultSet.getBoolean("is_available")) {
                    String borrowQuery = "INSERT INTO borrowed_books (customer_id, book_id, borrow_date) VALUES (?, ?, NOW())";
                    PreparedStatement borrowStmt = connection.prepareStatement(borrowQuery);
                    borrowStmt.setInt(1, customerId);
                    borrowStmt.setInt(2, bookId);
                    int rowsInserted = borrowStmt.executeUpdate();

                    if (rowsInserted > 0) {
                        String updateAvailabilityQuery = "UPDATE books SET is_available = false WHERE book_id = ?";
                        PreparedStatement updateStmt = connection.prepareStatement(updateAvailabilityQuery);
                        updateStmt.setInt(1, bookId);
                        updateStmt.executeUpdate();

                        System.out.println("Book borrowed successfully!");
                        return true;
                    }
                } else {
                    System.out.println("Book is currently unavailable.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {

                String returnQuery = "DELETE FROM borrowed_books WHERE book_id = ?";
                PreparedStatement returnStmt = connection.prepareStatement(returnQuery);
                returnStmt.setInt(1, bookId);
                int rowsDeleted = returnStmt.executeUpdate();

                if (rowsDeleted > 0) {
                    // Update the availability of the book
                    String updateAvailabilityQuery = "UPDATE books SET is_available = true WHERE book_id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateAvailabilityQuery);
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    System.out.println("Book returned successfully!");
                    return true;
                } else {
                    System.out.println("Failed to return the book. Book may not be borrowed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
