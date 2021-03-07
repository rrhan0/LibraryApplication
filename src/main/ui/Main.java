package ui;

import java.io.FileNotFoundException;

// Starts LibraryApp
// CITATION: many parts from JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
            new LibraryApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
