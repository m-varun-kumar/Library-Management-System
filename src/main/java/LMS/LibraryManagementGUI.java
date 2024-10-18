package LMS;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LibraryManagementGUI extends JFrame {
    private BookManager bookManager;
    private CustomerManager customerManager;
    private BorrowManager borrowManager;

    public LibraryManagementGUI() {
        bookManager = new BookManager();
        customerManager = new CustomerManager();
        borrowManager = new BorrowManager();
        initializeUI();
    }

    public void initializeUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField customerIdField = new JTextField(10);
        panel.add(customerIdField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Customer Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField customerNameField = new JTextField(10);
        panel.add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Customer Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField customerEmailField = new JTextField(10);
        panel.add(customerEmailField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Customer Phone:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField customerPhoneField = new JTextField(10);
        panel.add(customerPhoneField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Book ID:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField bookIdField = new JTextField(10);
        panel.add(bookIdField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addCustomerButton = new JButton("Add Customer");
        panel.add(addCustomerButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton borrowBookButton = new JButton("Borrow Book");
        panel.add(borrowBookButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton listBooksButton = new JButton("List Books");
        panel.add(listBooksButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        JButton returnBookButton = new JButton("Return Book");
        panel.add(returnBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextArea outputArea = new JTextArea(8, 40);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, gbc);

        addCustomerButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                String name = customerNameField.getText();
                String email = customerEmailField.getText();
                String phone = customerPhoneField.getText();
                Customer customer = new Customer(customerId, name, email, phone);
                customerManager.addCustomer(customer);
                outputArea.append("Customer added: " + name + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Please enter a valid Customer ID.\n");
            }
        });

        listBooksButton.addActionListener(e -> {
            List<Book> books = bookManager.getAllBooks();
            outputArea.setText("");
            books.forEach(book -> outputArea.append(book.toString() + "\n"));
        });

        borrowBookButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                boolean success = borrowManager.borrowBook(customerId, bookId);
                if (success) {
                    outputArea.append("Book borrowed with ID: " + bookId + "\n");
                } else {
                    outputArea.append("Failed to borrow book with ID: " + bookId + ". It may not be available.\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Please enter valid numeric IDs for Customer and Book.\n");
            }
        });

        returnBookButton.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                boolean success = borrowManager.returnBook(bookId);
                if (success) {
                    outputArea.append("Book returned with ID: " + bookId + "\n");
                } else {
                    outputArea.append("Failed to return book with ID: " + bookId + ". It may not be borrowed.\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Please enter a valid numeric Book ID.\n");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManagementGUI();
    }
}
