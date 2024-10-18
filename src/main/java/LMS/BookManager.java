package LMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.util.ArrayList;
import java.util.*;
public class BookManager {
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM books";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("book_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String isbn = resultSet.getString("isbn");
                    String publisher = resultSet.getString("publisher");
                    boolean isAvailable = resultSet.getBoolean("is_available");

                    Book book = new Book(id, title, author, isbn, publisher, isAvailable);
                    bookList.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + keyword + "%");
                preparedStatement.setString(2, "%" + keyword + "%");

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("book_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String isbn = resultSet.getString("isbn");
                    String publisher = resultSet.getString("publisher");
                    boolean isAvailable = resultSet.getBoolean("is_available");
                    Book book = new Book(id, title, author, isbn, publisher, isAvailable);
                    searchResults.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return searchResults;
    }
}
