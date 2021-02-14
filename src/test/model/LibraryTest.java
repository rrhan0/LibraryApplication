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
        Book book1 = new Book("title", "author", "body");
        assertTrue(library.addBook(book1));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        Book book2 = new Book("title", "different author", "different body");
        assertFalse(library.addBook(book2));
        assertEquals(1, library.getSize());
        assertFalse(library.contains(book2));

        Book book3 = new Book("different title", "author", "body");
        assertTrue(library.addBook(book3));
        assertEquals(2, library.getSize());
        assertTrue(library.contains(book3));
    }

    @Test
    void removeBookTest() {
        Book book1 = new Book("title", "author", "body");
        library.addBook(book1);

        assertFalse(library.removeBook(1));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        assertFalse(library.removeBook(20));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        assertFalse(library.removeBook(-1));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        assertFalse(library.removeBook(-20));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        assertTrue(library.removeBook(0));
        assertEquals(0, library.getSize());
        assertFalse(library.contains(book1));
    }

    @Test
    void inRangeTest() {
        Book book1 = new Book("title", "author", "body");
        Book book2 = new Book("title2", "author", "body");

        library.addBook(book1);
        library.addBook(book2);

        assertTrue(library.inRange(0));
        assertTrue(library.inRange(1));
        assertFalse(library.inRange(2));
        assertFalse(library.inRange(-1));
        assertFalse(library.inRange(-10));
        assertFalse(library.inRange(10));
    }

    @Test
    void getCatalogueTest() {
        Book book1 = new Book("title", "author", "body");
        library.addBook(book1);
        assertEquals(1, library.getCatalogue().size());
        assertTrue(library.getCatalogue().contains(book1));
    }
}
