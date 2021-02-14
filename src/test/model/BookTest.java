package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setup() {
        book = new Book("", "", "");
    }

    @Test
    void setterGetterTest() {
        book.setTitle("title");
        book.setAuthor("author");
        book.setBody("body");

        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("body", book.getBody());
    }
}