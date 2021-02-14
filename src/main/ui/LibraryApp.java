package ui;


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
        String command;

        init();

        while (run) {
            displayMenu();
            command = input.nextLine();

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
            System.out.println("Invalid input.");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a book adding
    private void doAddBook() {
        System.out.println("Enter the title:");
        String title = input.nextLine();
        System.out.println("Enter the author's name:");
        String author = input.nextLine();
        System.out.println("Enter the body:");
        String body = input.nextLine();

        if (library.addBook(new Book(title, author, body))) {
            System.out.println("Book added");
        } else {
            System.out.println("Failed to add book");

        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a book removal
    private void doRemoveBook() {
        System.out.println("Enter the book's number");
        doListBook();
        String select = input.nextLine();
        if (isNumeric(select)) {
            int index = Integer.parseInt(select) - 1;
            if (library.removeBook(index)) {
                System.out.println("Book removed");
            } else {
                System.out.println("Failed to remove book");
            }
        }
    }

    // EFFECTS: conducts a book list display
    private void doListBook() {
        ArrayList<Book> catalogue = library.getCatalogue();

        if (library.getSize() == 0) {
            System.out.println("The catalogue is empty");
        } else {
            int index = 1;
            for (Book b : catalogue) {
                System.out.println("\t" + index + ". " + b.getTitle());
                index++;
            }
        }
    }

    // EFFECTS: conducts a book opening
    private void doOpenBook() {
        System.out.println("Select a book:");
        doListBook();

        String select = input.nextLine();
        if (isNumeric(select)) {
            int index = Integer.parseInt(select) - 1;
            if (library.inRange(index)) {
                Book book = library.getCatalogue().get(index);
                System.out.println("\n" + book.getTitle() + " by " + book.getAuthor());
                System.out.println("\n" + book.getBody());
            } else {
                System.out.println("Invalid input");
            }
        }
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

    // borrowed from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    // EFFECTS: produces true if string can be converted to int, false otherwise
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return false;
        }
    }

}
