package model;

import java.util.ArrayList;

// Represents a Library having a catalogue of books.
public class Library {
    private ArrayList<Book> catalogue;

    public Library() {
        catalogue = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: add book to the library and return true if its title is not already in the library, false otherwise
    public boolean addBook(Book b) {
        for (Book existingBook : catalogue) {
            String newBookTitle = b.getTitle();
            String existingBookTitle = existingBook.getTitle();
            if (newBookTitle.equals(existingBookTitle)) {
                return false;
            }
        }
        catalogue.add(b);
        return true;
    }

    // MODIFIES: this
    // EFFECT: remove indexed book and produce true if index is within size of catalogue
    public boolean removeBook(int index) {
        if (inRange(index)) {
            catalogue.remove(index);
            return true;
        } else {
            return false;
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

    // EFFECTS: returns arraylist of books
    public ArrayList<Book> getCatalogue() {
        return catalogue;
    }


}
