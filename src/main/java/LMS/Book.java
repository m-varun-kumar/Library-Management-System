package LMS;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private boolean isAvailable;
    public Book(int id, String title, String author, String isbn, String publisher, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.isAvailable = isAvailable;
    }
    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Publisher=" + publisher + ", Available=" + isAvailable + "]";
    }
}
