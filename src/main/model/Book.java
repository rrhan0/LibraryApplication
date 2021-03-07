package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a book having a title author and body
public class Book implements Writable {
    String title;
    String author;
    String body;

    public Book(String title, String author, String body) {
        this.title = title;
        this.author = author;
        this.body = body;
    }

    // EFFECTS: returns book title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns book author
    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns book body
    public String getBody() {
        return body;
    }

    // EFFECTS: edits the contents of a book
    public void editBook(String title, String author, String body) {
        this.title = title;
        this.author = author;
        this.body = body;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("author", author);
        json.put("title", title);
        json.put("body", body);
        return json;
    }
}
