package persistence;

import model.Profile;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

// A class that reads from a file
public class Reader {
    public static final String path = Writer.path;
    private ArrayList<Profile> savedProfiles;
    File file;

    // Effects: Creates a new reader class with the given file
    // Modifies: this
    public Reader(File file) {
        this.file = file;
    }

    // Effects: Creates a new reader class with the default file
    // Modifies: this
    public Reader() {
        this.file = new File(path);
    }

    // Effects: Reads the given contents and returns a new profile with the given specifications
    public Profile readJsonObject(JSONObject contents) {
        return new Profile(""); // stub
    }

    // Effects: Reads the entire file and stores all profiles in savedProfiles
    // Modifies: this
    public void readFile() {
        // stub
    }

    public ArrayList<Profile> getSavedProfiles() {
        return savedProfiles;
    }
}
