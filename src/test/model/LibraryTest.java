package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library library;

    @BeforeEach
    void setup() {
        library = new Library();
    }

    @Test
    void addBookTest() {
        Book book = new Book("title", "author", "body");
        library.addBook(book);
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book));
    }

    @Test
    void removeBookTest() {
        Book book = new Book("title", "author", "body");
        library.addBook(book);
        library.removeBook(0);
        assertEquals(0, library.getSize());
        assertFalse(library.contains(book));
    }
}
