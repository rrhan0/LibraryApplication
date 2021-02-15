package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library library;
    Book book1;
    Book book2;
    Book book3;


    @BeforeEach
    void setup() {
        library = new Library();
        book1 = new Book("title", "author", "body");
        book2 = new Book("title2", "author2", "body");
        book3 = new Book("title3", "author3", "body3");
    }

    @Test
    void addBookTest() {
        assertEquals("Book added", library.addBook(book1));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

        Book newBook = new Book("title", "different author", "different body");
        assertEquals("This title already exists", library.addBook(newBook));
        assertEquals(1, library.getSize());
        assertFalse(library.contains(newBook));

        assertEquals("Book added", library.addBook(book3));
        assertEquals(2, library.getSize());
        assertTrue(library.contains(book3));
    }

    @Test
    void removeBookTest() {
        library.addBook(book1);
        library.addBook(book2);

        assertEquals("Index out of range", library.removeBook(-1));
        assertEquals("Index out of range", library.removeBook(-5));
        assertEquals("Index out of range", library.removeBook(2));
        assertEquals("Index out of range", library.removeBook(5));
        assertEquals(2, library.getSize());
        assertTrue(library.contains(book1));
        assertTrue(library.contains(book2));

        assertEquals("Book removed", library.removeBook(1));
        assertEquals(1, library.getSize());
        assertTrue(library.contains(book1));

    }

    @Test
    void inRangeTest() {
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
    void openBookTest() {
        library.addBook(book1);

        assertEquals("Index out of range", library.openBook(1));
        assertEquals("Index out of range", library.openBook(5));
        assertEquals("Index out of range", library.openBook(-1));
        assertEquals("Index out of range", library.openBook(-5));

        assertEquals("title" + " by " + "author" + "\n\nbody", library.openBook(0));
    }

    @Test
    void listBookTest() {
        assertEquals("The catalogue is empty", library.listBook());

        library.addBook(book1);
        assertEquals("1. title\n", library.listBook());

        library.addBook(book2);
        assertEquals("1. title\n2. title2\n", library.listBook());
    }

    @Test
    void updateBookTest() {
        library.addBook(book1);

        assertEquals("Index out of range", library.updateBook("new title", "new author",
                "new body", 1));
        assertEquals("Index out of range", library.updateBook("new title", "new author",
                "new body", -1));
        assertEquals("Index out of range", library.updateBook("new title", "new author",
                "new body", 4));
        assertEquals("Index out of range", library.updateBook("new title", "new author",
                "new body", -4));
        assertEquals("title", book1.getTitle());

        assertEquals("Book updated", library.updateBook("new title", "new author",
                "new body", 0));
        assertEquals("new title", book1.getTitle());
    }


}
