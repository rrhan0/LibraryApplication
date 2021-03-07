package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CITATION: from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Library wr = new Library();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library lib = new Library();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            lib = reader.read();
            assertEquals(0, lib.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library lib = new Library();
            lib.addBook(new Book("Metro 2033", "Dmitry Glukhovsky", "In the Moscow metro"));
            lib.addBook(new Book("The Big Switch", "Harry Turtledove", "WW2 but early"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            lib = reader.read();
            List<Book> books = lib.getCatalogue();
            assertEquals(2, books.size());
            checkBook("Metro 2033", "Dmitry Glukhovsky", "In the Moscow metro", books.get(0));
            checkBook("The Big Switch", "Harry Turtledove", "WW2 but early", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
