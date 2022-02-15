package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Writes given information to a file.
public class Writer {
    public static final String path = "./data/profiles.json";
    private File file;

    // Effects: Creates a new Writer class with the given File
    public Writer(File file) {
        this.file = file;
    }

    // Effects: Creates a new Writer class with the default path from SaveObject
    public Writer() {
        this.file = new File(path);
    }

    // Effects: Creates a new file with the stored file
    //          Throws IOException if file cannot be created
    public void createFile() throws IOException {

    }

    // Effects: Adds the given SaveObject to the file.
    //          Throws IOException if the file cannot be written to
    public void writeToFile() throws IOException {

    }

    // Effects: Clears the file
    //          Throws IOException if the file cannot be cleared
    public void clearFile() throws IOException {

    }
}
