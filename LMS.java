package LBM;
import java.util.ArrayList;
import java.util.Scanner;

// Book class to hold book details
class Book {
    int id;
    String title;
    boolean isBorrowed;
    String borrowerName;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
        this.borrowerName = "";
    }
}

// Library class to manage books
class Library {
    ArrayList<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    void showBooks() {
        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            String status = b.isBorrowed ? "Not Available" : "Available";
            System.out.println("ID: " + b.id + " | Title: " + b.title + " | Status: " + status);
        }
    }

    void borrowBook(int bookId, String borrower) {
        for (Book b : books) {
            if (b.id == bookId) {
                if (!b.isBorrowed) {
                    b.isBorrowed = true;
                    b.borrowerName = borrower;
                    System.out.println("✅ You borrowed: " + b.title);
                    return;
                } else {
                    System.out.println("❌ Book is already borrowed.");
                    return;
                }
            }
        }
        System.out.println("❌ Book ID not found.");
    }

    void returnBook(int bookId) {
        for (Book b : books) {
            if (b.id == bookId) {
                if (b.isBorrowed) {
                    b.isBorrowed = false;
                    b.borrowerName = "";
                    System.out.println("✅ You returned: " + b.title);
                    return;
                } else {
                    System.out.println("❌ That book wasn't borrowed.");
                    return;
                }
            }
        }
        System.out.println("❌ Book ID not found.");
    }
}

// Main class
public class LBM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding some books to the library
        library.addBook(new Book(1, "Java for Beginners"));
        library.addBook(new Book(2, "Python 101"));
        library.addBook(new Book(3, "Data Structures"));

        int choice;
        do {
            System.out.println("\n📚 Library Menu:");
            System.out.println("1. Show Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> library.showBooks();
                case 2 -> {
                    System.out.print("Enter Book ID to borrow: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    library.borrowBook(id, name);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = scanner.nextInt();
                    library.returnBook(id);
                }
                case 4 -> System.out.println("👋 Thank you for using the Library!");
                default -> System.out.println("❗ Invalid choice. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

