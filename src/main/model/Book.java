package model;

// Represents a book having a title author and body
public class Book {
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
}
