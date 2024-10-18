package LMS;

import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        CustomerManager customerManager = new CustomerManager();
        BorrowManager borrowManager = new BorrowManager();
        LibraryManagementGUI gui = new LibraryManagementGUI();
        gui.setVisible(true);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n====> Library Management System <====");
            System.out.println("1. List Books");
            System.out.println("2. Search Book");
            System.out.println("3. Add Customer");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    List<Book> books = bookManager.getAllBooks();
                    books.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Enter keyword (title or author):");
                    String keyword = sc.nextLine();
                    List<Book> searchResults = bookManager.searchBooks(keyword);
                    searchResults.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter customer ID:");
                    int customerId=sc.nextInt();
                    System.out.println("Enter customer name:");
                    String name = sc.nextLine();
                    System.out.println("Enter customer email:");
                    String email = sc.nextLine();
                    System.out.println("Enter customer phone:");
                    String phone = sc.nextLine();
                    Customer customer = new Customer(customerId ,name, email, phone);
                    customerManager.addCustomer(customer);
                    break;
                case 4:
                    System.out.println("Enter customer ID:");
                    customerId = sc.nextInt();
                    System.out.println("Enter book ID:");
                    int bookId = sc.nextInt();
                    borrowManager.borrowBook(customerId, bookId);
                    break;
                case 5:
                    System.out.println("Enter book ID to return:");
                    int returnBookId = sc.nextInt();
                    borrowManager.returnBook(returnBookId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
