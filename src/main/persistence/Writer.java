package persistence;

import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Writes given information to a file.
public class Writer {
    public static final String PATH = "./data/profiles.json";
    private File file;
    private JSONArray savedObjects;

    // Effects: Creates a new Writer class with the given File
    public Writer(File file) {
        this.file = file;
        this.savedObjects = new JSONArray();
    }

    // Effects: Creates a new Writer class with the default path from SaveObject
    public Writer() {
        this.file = new File(PATH);
        this.savedObjects = new JSONArray();
    }

    // Effects: Creates a new file with the stored file
    //          Throws IOException if file cannot be created
    public void createFile() throws IOException {
        file.createNewFile();
    }

    // Effects: Adds the given SaveObject to the savedObjects JSONArray
    // Modifies: this
    public void addSaveObject(SaveObject saveObject) {
        savedObjects.put(saveObject.getNewContents());
    }

    // Effects: Adds the given SaveObject to the file.
    //          Throws IOException if the file cannot be written to.
    // Modifies: this.file
    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(savedObjects.toString());
        fileWriter.close();
    }

    // Effects: Clears the file
    //          Throws IOException if the file cannot be cleared.
    // Modifies: this.file
    public void clearFile() throws IOException {
        new FileWriter(file, false).close();
    }

    public JSONArray getSavedObjects() {
        return savedObjects;
    }

    public File getFile() {
        return this.file;
    }
}
