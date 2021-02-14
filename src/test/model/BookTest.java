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
}
