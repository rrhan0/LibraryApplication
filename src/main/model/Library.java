package model;

import java.util.ArrayList;

// Represents a Library having a catalogue of books.
public class Library {
    private ArrayList<Book> catalogue;

    public Library() {
        catalogue = new ArrayList<Book>();
    }

    // MODIFIES: this
    // EFFECT: add book to the library
    public void addBook(Book b) {
        catalogue.add(b);
    }

    // MODIFIES: this
    // EFFECT: remove the book with given index from the library
    public void removeBook(int index) {
        catalogue.remove(index);
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
