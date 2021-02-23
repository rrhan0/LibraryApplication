package persistence;

import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library lib = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

}
