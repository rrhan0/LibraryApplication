package model;

import exception.EmptyListException;
import exception.IndexException;
import exception.UniqueTitleException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a Library having a catalogue of books.
public class Library implements Writable {
    private ArrayList<Book> catalogue;

    // EFFECTS: contructs a library with a catalogue of books
    public Library() {
        catalogue = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: add book to the library if it has unique non-empty title,
    //         otherwise throw UniqueTitleException
    public void addBook(Book b) throws UniqueTitleException {
        if (b.getTitle().equals("")) {
            throw new UniqueTitleException();
        }
        for (Book existingBook : catalogue) {
            String newBookTitle = b.getTitle();
            String existingBookTitle = existingBook.getTitle();
            if (newBookTitle.equals(existingBookTitle)) {
                throw new UniqueTitleException();
            }
        }
        catalogue.add(b);
    }

    // MODIFIES: this
    // EFFECT: remove indexed book if index is appropriate,
    //         throw IndexException otherwise
    public void removeBook(int index) throws IndexException {
        if (inRange(index)) {
            catalogue.remove(index);
        } else {
            throw new IndexException();
        }
    }

    // EFFECTS: produce string of index numbered list of book titles,
    //          or throw EmptyListException if catalogue is empty
    public String listBook() throws EmptyListException {
        StringBuilder bookList = new StringBuilder();

        if (getSize() == 0) {
            throw new EmptyListException();
        }
        int index = 1;
        for (Book b : catalogue) {
            bookList.append(index).append(". ").append(b.getTitle()).append("\n");
            index++;
        }
        return bookList.toString();
    }

    // EFFECTS: returns the contents of a book to read if index in range,
    //          throw IndexException otherwise
    public String openBook(int index) throws IndexException {
        if (inRange(index)) {
            Book book = catalogue.get(index);
            return book.getTitle() + " by " + book.getAuthor() + "\n\n" + book.getBody();
        } else {
            throw new IndexException();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the contents of a book and returns the result if index in range,
    //          throw IndexException otherwise
    public void updateBook(String title, String author, String body, int index) throws IndexException {
        if (inRange(index)) {
            Book book = catalogue.get(index);
            book.editBook(title, author, body);
        } else {
            throw new IndexException();
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("catalogue", catalogueToJson());
        return json;
    }

    // EFFECTS: returns books in this library as a JSON array
    private JSONArray catalogueToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : catalogue) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns the list of books in library
    public ArrayList<Book> getCatalogue() {
        return catalogue;
    }
}
