package com.library.LibraryManagement.service;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> searchBooks(String query) {
        return bookRepository
                .findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public Book createBook(Book book) {
        book.setAvailableQuantity(book.getQuantity());
        return bookRepository.save(book);
    }

    public void initializeBooks() {
        if (bookRepository.count() == 0) {
            List<Book> books = Arrays.asList(
                    /* ================= COMPUTER SCIENCE ================= */
                    new Book("CS001", "Java: A Beginner's Guide", "Herbert Schildt", "8th Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780071606301-L.jpg?default=false"),
                    new Book("CS002", "The Pragmatic Programmer", "Andrew Hunt & David Thomas", "1st Edition", 4, 4, "https://covers.openlibrary.org/b/id/15136784-L.jpg"),
                    new Book("CS003", "Code Complete", "Steve McConnell", "2nd Edition", 6, 6, "https://covers.openlibrary.org/b/isbn/9780735619678-L.jpg?default=false"),
                    new Book("CS004", "Design Patterns", "Erich Gamma | John Vlissides | Richard Helm | Ralph Johnson", "1st Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9780201633610-L.jpg?default=false"),
                    new Book("CS005", "Refactoring", "Martin Fowler", "1st Edition", 7, 7, "https://covers.openlibrary.org/b/isbn/9780201485677-L.jpg?default=false"),
                    new Book("CS006", "Introduction to Algorithms", "Cormen et al.", "3rd Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780262033848-L.jpg?default=false"),
                    new Book("CS007", "Grokking Algorithms", "Aditya Y. Bhargava", "1st Edition", 4, 4, "https://covers.openlibrary.org/b/isbn/9781617292231-L.jpg?default=false"),
                    new Book("CS008", "The Art of Computer Programming", "Donald Knuth", "3rd Edition", 6, 6, "https://covers.openlibrary.org/b/isbn/9780201896831-L.jpg?default=false"),
                    new Book("CS009", "Computer Networking", "Kurose & Ross", "7th Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780133594140-L.jpg?default=false"),
                    new Book("CS010", "Operating System Concepts", "Abraham Silberschatz | Peter Baer Galvin | Greg Gagne", "9th Edition", 2, 2, "https://covers.openlibrary.org/b/isbn/9781118063330-L.jpg?default=false"),
                    new Book("CS011", "The C Programming Language", "Brian W. Kernighan | Dennis M. Ritchie", "2nd Edition", 4, 4, "https://covers.openlibrary.org/b/isbn/9780131103627-L.jpg?default=false"),
                    new Book("CS012", "Effective Java", "Joshua Bloch", "3rd Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780134685991-L.jpg?default=false"),
                    new Book("CS013", "Head First Java", "Kathy Sierra & Bert Bates", "2nd Edition", 6, 6, "https://covers.openlibrary.org/b/isbn/9780596009205-L.jpg?default=false"),
                    new Book("CS014", "Python Crash Course", "Eric Matthes", "2nd Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9781593279288-L.jpg?default=false"),
                    new Book("CS015", "Database System Concepts", "Abraham Silberschatz | Henry F. Korth | S. Sudarshan", "7th Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9780073523323-L.jpg?default=false"),
                    new Book("CS016", "Artificial Intelligence", "Stuart Russell & Peter Norvig", "3rd Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780136042594-L.jpg?default=false"),
                    new Book("CS017", "Machine Learning", "Tom M. Mitchell", "1st Edition", 2, 2, "https://covers.openlibrary.org/b/isbn/9780070428072-L.jpg?default=false"),
                    new Book("CS018", "Deep Learning", "Ian Goodfellow | Yoshua Bengio | Aaron Courville", "1st Edition", 4, 4, "https://covers.openlibrary.org/b/isbn/9780262035613-L.jpg?default=false"),
                    new Book("CS019", "Spring in Action", "Craig Walls", "5th Edition", 6, 6, "https://covers.openlibrary.org/b/isbn/9781617294945-L.jpg?default=false"),
                    new Book("CS020", "React Up & Running", "Stoyan Stefanov", "2nd Edition", 5, 5, "https://ia801909.us.archive.org/view_archive.php?archive=/31/items/l_covers_0013/l_covers_0013_91.zip&file=0013919031-L.jpg"),
                    new Book("CS021", "System Design Interview", "Alex Xu & Sahn Lam", "2nd Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9781736049112-L.jpg?default=false"),
                    new Book("CS022", "Linux Kernel Development", "Robert Love", "2nd Edition", 4, 4, "https://ia601405.us.archive.org/view_archive.php?archive=/22/items/olcovers41/olcovers41-L.zip&file=411072-L.jpg"),
                    new Book("CS023", "Cloud Computing", "Rajkumar Buyya | James Broberg | Andrzej Goscinski", "1st Edition", 6, 6, "https://m.media-amazon.com/images/I/919n-4OwBoL.jpg"),
                    new Book("CS024", "Blockchain Basics", "Daniel Drescher", "1st Edition", 2, 2, "https://covers.openlibrary.org/b/isbn/9781484226032-L.jpg?default=false"),
                    new Book("CS025", "Computer Organization and Design", "David A. Patterson & John L. Hennessy", "4th Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780123747501-L.jpg?default=false"),
                    new Book("CS026", "Compilers", "Alfred V. Aho | Monica S. Lam | Ravi Sethi | Jeffrey D. Ullman", "2nd Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9780321486813-L.jpg?default=false"),
                    new Book("CS027", "Data Mining", "Jiawei Han | Micheline Kamber | Jian Pei", "3rd Edition", 4, 4, "https://covers.openlibrary.org/b/isbn/9780123814791-L.jpg?default=false"),
                    new Book("CS028", "Designing Data-Intensive Applications", "Martin Kleppmann", "1st Edition", 2, 2, "https://covers.openlibrary.org/b/isbn/9781449373320-L.jpg?default=false"),
                    new Book("CS029", "Cyber Security Essentials", "William Stallings", "6th Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9780134527338-L.jpg?default=false"),
                    new Book("CS030", "Software Engineering", "Roger S. Pressman & Bruce R. Maxim", "8th Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780078022128-L.jpg?default=false"),

                    /* ================= MBA ================= */
                    new Book("MBA001", "The Personal MBA", "Josh Kaufman", "1st Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9781591843528-L.jpg?default=false"),
                    new Book("MBA002", "Principles of Marketing", "Philip Kotler & Gary Armstrong", "17th Edition", 4, 4, "https://covers.openlibrary.org/b/isbn/9780134492513-L.jpg?default=false"),
                    new Book("MBA003", "Marketing Management", "Philip Kotler & Kevin Lane Keller", "15th Edition", 6, 6, "https://covers.openlibrary.org/b/id/15090688-L.jpg"),
                    new Book("MBA004", "Financial Management", "Eugene F. Brigham & Michael C. Ehrhardt", "12th Edition", 5, 5, "https://ia800404.us.archive.org/view_archive.php?archive=/33/items/l_covers_0010/l_covers_0010_55.zip&file=0010556066-L.jpg"),
                    new Book("MBA005", "Organizational Behavior", "Stephen P. Robbins & Timothy A. Judge", "18th Edition", 3, 3, "https://covers.openlibrary.org/b/isbn/9780134729329-L.jpg?default=false"),
                    new Book("MBA006", "Operations Management", "William J. Stevenson", "11th Edition", 4, 4, "https://ia800404.us.archive.org/view_archive.php?archive=/33/items/l_covers_0010/l_covers_0010_23.zip&file=0010237960-L.jpg"),
                    new Book("MBA007", "Business Analytics", "James Evans", "3rd Edition", 6, 6, "https://covers.openlibrary.org/b/isbn/9780135231678-L.jpg?default=false"),
                    new Book("MBA008", "Strategic Management", "Thompson Strickland", "9th Edition", 5, 5, "https://ia800104.us.archive.org/view_archive.php?archive=/30/items/olcovers234/olcovers234-L.zip&file=2341123-L.jpg"),
                    new Book("MBA009", "Human Resource Management", "Gary Dessler", "7th Edition", 4, 4, "https://ia902800.us.archive.org/view_archive.php?archive=/26/items/olcovers711/olcovers711-L.zip&file=7118942-L.jpg"),
                    new Book("MBA010", "Business Communication Today", "Courtland L. Bovee & John V. Thill", "14th Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780134562186-L.jpg?default=false"),

                    /* ================= AEROSPACE ================= */
                    new Book("AE001", "Fundamentals of Aerospace Engineering: (Beginner's Guide)", "Ali Baghchehsara | Francisco Gallardo Lopez | Jens Strahmann", "1st Edition", 3, 3, "https://ia800404.us.archive.org/view_archive.php?archive=/33/items/l_covers_0010/l_covers_0010_22.zip&file=0010229060-L.jpg"),

                    /* ================= SCIENCE ================= */
                    new Book("SCI001", "Biology: Concepts and Applications", "Cecie Starr | Christine Evers | Lisa Starr", "9th Edition", 5, 5, "https://ia800100.us.archive.org/view_archive.php?archive=/5/items/l_covers_0012/l_covers_0012_65.zip&file=0012653955-L.jpg"),
                    new Book("SCI002", "Microbiology", "Gerard J. Tortora | Berdell R. Funke | Christine L. Case", "8th Edition", 3, 3, "https://covers.openlibrary.org/b/id/15099083-L.jpg"),
                    new Book("SCI003", "Physics for Scientists and Engineers", "Paul A. Tipler", "4th Edition", 5, 5, "https://ia800502.us.archive.org/view_archive.php?archive=/17/items/olcovers82/olcovers82-L.zip&file=823021-L.jpg"),

                    /* ================= PHARMACY ================= */
                    new Book("PH001", "Pharmacotherapy: A Pathophysiologic Approach", "Joseph T. DiPiro | Robert L. Talbert | Gary C. Yee | Barbara G. Wells", "3rd Edition", 4, 4, "https://ia600507.us.archive.org/view_archive.php?archive=/8/items/l_covers_0009/l_covers_0009_26.zip&file=0009266563-L.jpg"),
                    new Book("PH002", "Martindale: The Complete Drug Reference", "Sean Sweetman", "35th Edition", 2, 2, "https://ia600805.us.archive.org/view_archive.php?archive=/35/items/olcovers160/olcovers160-L.zip&file=1604448-L.jpg"),
                    new Book("PH003", "Pharmacotherapy Casebook", "Terry L. Schwinghammer", "5th Edition", 3, 3, "https://ia601701.us.archive.org/view_archive.php?archive=/4/items/l_covers_0011/l_covers_0011_69.zip&file=0011694470-L.jpg"),
                    new Book("PH004", "Applied Therapeutics: The Clinical Use of Drugs", "Caroline S. Zeind | Michael G. Carvalho | Judy W. M. Cheng | Kathy Zaiken | Trisha LaPointe", "12th Edition", 2, 2, "https://covers.openlibrary.org/b/id/14793734-L.jpg"),
                    new Book("PH005", "Pharmacy Management: Essentials for All Practice Settings", "Shane P. Desselle | David P. Zgarrick", "2nd Edition", 4, 4, "https://ia801909.us.archive.org/view_archive.php?archive=/31/items/l_covers_0013/l_covers_0013_83.zip&file=0013830432-L.jpg"),
                    new Book("PH006", "Clinical Skills for Pharmacists", "Karen J. Tietze", "3rd Edition", 5, 5, "https://covers.openlibrary.org/b/isbn/9780323077385-L.jpg?default=false")
            );
            bookRepository.saveAll(books);
            System.out.println("✅ Successfully initialized " + books.size() + " books with alphanumeric IDs!");
        }
    }
}