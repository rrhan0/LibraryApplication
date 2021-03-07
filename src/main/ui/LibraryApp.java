package ui;


import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Library Application
public class LibraryApp {
    private static final String JSON_STORE = "./data/library.json";
    private Library library;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the library application
    public LibraryApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Code based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runLibrary() {
        boolean run = true;
        String command;

        init();

        while (run) {
            displayMenu();
            command = input.nextLine();

            if (command.equals("q")) {
                doSaveLibrary();
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
        doLoadLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes commands
    // Code based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddBook();
        } else if (command.equals("2")) {
            doRemoveBook();
        } else if (command.equals("3")) {
            doListBook();
        } else if (command.equals("4")) {
            doOpenBook();
        } else if (command.equals("5")) {
            doEditBook();
        } else {
            System.out.println("Invalid input.");
        }
    }

    // EFFECTS: menu of options to user
    // Code based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\t1. Add book");
        System.out.println("\t2. Remove book");
        System.out.println("\t3. List book");
        System.out.println("\t4. Open book");
        System.out.println("\t5. Edit book");
        System.out.println("\tq. Quit");
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

        System.out.println(library.addBook(new Book(title, author, body)));
    }

    // MODIFIES: this
    // EFFECTS: conducts a book removal
    private void doRemoveBook() {
        int index = selectBook();

        if (index != -1) {
            System.out.println(library.removeBook(index));
        }
    }

    // EFFECTS: conducts a book listing
    private void doListBook() {
        System.out.println(library.listBook());
    }

    // EFFECTS: conducts a book opening
    private void doOpenBook() {
        int index = selectBook();

        if (index != -1) {
            System.out.println(library.openBook(index));
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a book editing
    private void doEditBook() {
        int index = selectBook();

        if (library.inRange(index)) {
            System.out.println("Enter the title:");
            String title = input.nextLine();
            System.out.println("Enter the author's name:");
            String author = input.nextLine();
            System.out.println("Enter the body:");
            String body = input.nextLine();

            library.updateBook(title, author, body, index);
        } else {
            System.out.println("Index out of range");
        }
    }

    // EFFECTS: Takes input, if str can be int, return the adjusted index, else return -1
    private int selectBook() {
        System.out.println("Enter the book's number:");
        doListBook();
        String select = input.nextLine();
        if (isNumeric(select)) {
            return Integer.parseInt(select) - 1;
        } else {
            return -1;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from file
    public void doLoadLibrary() {
        try {
            library = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves library to file
    public void doSaveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: produces true if string can be converted to int, false otherwise
    // borrowed from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input isn't integer");
            return false;
        }
    }
}
