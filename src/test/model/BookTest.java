package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    Book book;

    @Test
    void getterTest() {
        book = new Book("title", "author", "body");

        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("body", book.getBody());
    }

    @Test void editBookTest() {
        book = new Book("title", "author", "body");

        book.editBook("Book name", "Richard", "Something");

        assertEquals("Book name", book.getTitle());
        assertEquals("Richard", book.getAuthor());
        assertEquals("Something", book.getBody());

    }
}
