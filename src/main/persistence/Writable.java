package persistence;

import org.json.JSONObject;

// CITATION: many parts from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
