package ui;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import model.Book;
import model.Library;

import java.util.ArrayList;
import java.util.Scanner;

// Library Application
// Code based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class LibraryApp {
    private Library library;
    private Scanner input;

    // EFFECTS: runs the library application
    public LibraryApp() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibrary() {
        boolean run = true;
        String command = null;

        init();

        while (run) {
            displayMenu();
            command = input.next();

            if (command.equals("5")) {
                run = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    private void init() {
        library = new Library();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes commands
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddBook();
        } else if (command.equals("2")) {
            doRemoveBook();
        } else if (command.equals("3")) {
            doListBook();
        } else if (command.equals("4")) {
            doOpenBook();
        } else {
            System.out.println("Option not valid.");
        }
    }



    private void doAddBook() {
        System.out.println("Enter the title:");
        String title = input.next();
        System.out.println("Enter the author's name:");
        String author = input.next();
        System.out.println("Enter the body:");
        String body = input.next();

        library.addBook(new Book(title, author, body));

    }

    // MODIFIES: this
    // EFFECTS: conducts a book removal
    private void doRemoveBook() {
        doListBook();
        System.out.println("Enter the book's number");
        int index = input.nextInt() - 1;
        library.removeBook(index);
    }

    // EFFECTS: conducts a book list display
    private void doListBook() {
        ArrayList<Book> catalogue = library.getCatalogue();

        int index = 1;
        for (Book b : catalogue) {
            System.out.println("\t" + index + ". " + b.getTitle());
            index++;
        }
        goBack();
    }

    // EFFECTS: conducts a book opening
    private void doOpenBook() {
        System.out.println("Select a book:");
        doListBook();
        int index = input.nextInt() - 1;
        Book book = library.getCatalogue().get(index);

        System.out.println("\n" + book.getTitle() + " by " + book.getAuthor());
        System.out.println("\n" + book.getBody());
        goBack();

    }

    // EFFECTS: menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\t1. Add book");
        System.out.println("\t2. Remove book");
        System.out.println("\t3. List book");
        System.out.println("\t4. Open book");
        System.out.println("\t5. Quit");
    }

    private void goBack() {
        boolean stay = true;
        while (stay) {
            System.out.println("\nPress q to go back to menu");
            String command = input.next();

            if (command.equals("q")) {
                stay = false;
            } else {
                System.out.println("Option not valid.");
            }
        }
    }
}
