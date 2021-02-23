package model;

import java.util.ArrayList;

// Represents a Library having a catalogue of books.
public class Library {
    private ArrayList<Book> catalogue;

    public Library() {
        catalogue = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: add book to the library if it has unique title and return result
    public String addBook(Book b) {
        for (Book existingBook : catalogue) {
            String newBookTitle = b.getTitle();
            String existingBookTitle = existingBook.getTitle();
            if (newBookTitle.equals(existingBookTitle)) {
                return "This title already exists";
            }
        }
        catalogue.add(b);
        return "Book added";
    }

    // MODIFIES: this
    // EFFECT: remove indexed book and return string with result
    public String removeBook(int index) {
        if (inRange(index)) {
            catalogue.remove(index);
            return "Book removed";
        } else {
            return "Index out of range";
        }
    }

    // EFFECTS: produce string of index numbered list of book titles, or return empty
    public String listBook() {
        String bookList = "";

        int index = 1;
        for (Book b : catalogue) {
            bookList = index + ". " + b.getTitle() + "\n";
            index++;
        }
        if (bookList.equals("")) {
            return "The catalogue is empty";
        } else {
            return bookList;
        }
    }

    // EFFECTS: returns the contents of a book to read if index in range, error otherwise
    public String openBook(int index) {
        if (inRange(index)) {
            Book book = catalogue.get(index);
            return book.getTitle() + " by " + book.getAuthor() + "\n\n" + book.getBody();
        } else {
            return "Index out of range";
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the contents of a book and returns the result
    public String updateBook(String title, String author, String body, int index) {
        if (inRange(index)) {
            Book book = catalogue.get(index);
            book.editBook(title, author, body);
            return "Book updated";
        } else {
            return "Index out of range";
        }
    }

    // EFFECTS: produce true if index is within range of catalogue
    public boolean inRange(int index) {
        return index >= 0 && index <= catalogue.size() - 1;
    }

    // EFFECTS: returns size of catalogue
    public int getSize() {
        return catalogue.size();
    }

    // EFFECTS: produces true if given book is in catalogue
    public boolean contains(Book b) {
        return catalogue.contains(b);
    }
}
