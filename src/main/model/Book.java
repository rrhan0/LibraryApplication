package model;

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

    // EFFECTS: set book title
    public void setTitle(String title) {
        this.title = title;
    }

    // EFFECTS: returns book author
    public String getAuthor() {
        return author;
    }

    // EFFECTS: set book author
    public void setAuthor(String author) {
        this.author = author;
    }

    // EFFECTS: returns book body
    public String getBody() {
        return body;
    }

    // EFFECTS: returns book body
    public void setBody(String body) {
        this.body = body;
    }
}
