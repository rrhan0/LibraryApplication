package persistence;

import model.Library;
import org.json.JSONObject;

import java.io.IOException;

// Represents a reader that reads workroom from JSON data stored in file
// CITATION: many parts from JsonSerializationDemo
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads workroom from file and returns it
    public Library read() {
        return new Library();
    }

    // EFFECTS: parses library from JSON object and returns it
    // throws IOException if an error occurs reading data from file
    private Library parseLibrary(JSONObject jsonObject) throws IOException {
        return new Library();
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source)  throws IOException {
        return  "";
    }

    // MODIFIES: lib
    // EFFECTS: parses catalogue from JSON object and adds them to workroom
    private void addBooks(Library lib, JSONObject jsonObject) {
    }

    // MODIFIES: lib
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(Library lib, JSONObject jsonObject) {

    }
}
