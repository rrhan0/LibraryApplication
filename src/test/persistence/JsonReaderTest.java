package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals(0, lib.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            Library lib = reader.read();
            List<Book> books = lib.getCatalogue();
            assertEquals(2, books.size());
            checkBook("Harry Potter", "JK Rowling", "magic", books.get(0));
            checkBook("the best book", "Richard", "this is the best book", books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
