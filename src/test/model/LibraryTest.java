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
        try {
            library.addBook(book1);
            assertEquals(1, library.getSize());
            assertTrue(library.contains(book1));

            library.addBook(book3);
            assertEquals(2, library.getCatalogue().size());
            assertTrue(library.contains(book3));
        } catch (Exception e) {
            fail("Exception not expected");
        }

        try {
            Book newBook = new Book("title", "different author", "different body");
            library.addBook(newBook);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }

        try {
            Book newBook = new Book("", "different author", "different body");
            library.addBook(newBook);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void removeBookTest() {
        try {
            library.addBook(book1);
            library.addBook(book2);
        } catch (Exception e) {
            fail("Exception not expected");
        }

        try {
            library.removeBook(-1);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.removeBook(-5);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.removeBook(2);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.removeBook(5);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }

        assertEquals(2, library.getSize());
        assertTrue(library.contains(book1));
        assertTrue(library.contains(book2));

        try {
            library.removeBook(1);
            assertEquals(1, library.getSize());
            assertTrue(library.contains(book1));
        } catch (Exception e) {
            fail("No exception expected");
        }

    }

    @Test
    void inRangeTest() {
        try {
            library.addBook(book1);
            library.addBook(book2);
        } catch (Exception e) {
            fail("Exception not expected");
        }

        assertTrue(library.inRange(0));
        assertTrue(library.inRange(1));
        assertFalse(library.inRange(2));
        assertFalse(library.inRange(-1));
        assertFalse(library.inRange(-10));
        assertFalse(library.inRange(10));
    }

    @Test
    void openBookTest() {
        try {
            library.addBook(book1);
        } catch (Exception e) {
            fail("Exception expected");
        }

        try {
            library.openBook(1);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.openBook(5);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.openBook(-1);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.openBook(-5);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            assertEquals("title" + " by " + "author" + "\n\nbody", library.openBook(0));
        } catch (Exception e) {
            fail("Exception not expected");
        }
    }

    @Test
    void listBookTest() {
        try {
            library.listBook();
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.addBook(book1);
            assertEquals("1. title\n", library.listBook());
            library.addBook(book2);
            assertEquals("1. title\n2. title2\n", library.listBook());
        } catch (Exception e) {
            fail("Exception not expected");
        }
    }

    @Test
    void updateBookTest() {
        try {
            library.addBook(book1);
        } catch (Exception e) {
            fail("Exception not expected");
        }

        try {
            library.updateBook("new title", "new author", "new body", 1);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.updateBook("new title", "new author", "new body", -1);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.updateBook("new title", "new author", "new body", 4);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        try {
            library.updateBook("new title", "new author", "new body", -4);
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
        assertEquals("title", book1.getTitle());

        try {
            library.updateBook("new title", "new author", "new body", 0);
            assertEquals("new title", book1.getTitle());
        } catch (Exception e) {
            fail("Exception not expected");
        }
    }


}
