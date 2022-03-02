package persistence;

import exceptions.ReadProfileException;
import model.Profile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// A class that reads from a file
public class Reader {
    public static final String PATH = Writer.PATH;
    private ArrayList<Profile> savedProfiles;
    File file;

    // Effects: Creates a new reader class with the given file
    // Modifies: this
    public Reader(File file) {
        this.file = file;
        this.savedProfiles = new ArrayList<>();
    }

    // Effects: Creates a new reader class with the default file
    // Modifies: this
    public Reader() {
        this.file = new File(PATH);
        this.savedProfiles = new ArrayList<>();
    }

    // Effects: Reads the given contents and returns a new profile with the given specifications
    //          Throws JSONException if JSON is unreadable.
    public Profile readJsonObject(JSONObject contents) {
        return new SaveObject(contents).getNewProfile();
    }

    // Effects: Reads the entire file and stores all profiles in savedProfiles.
    //          Throws ReadProfileException if sc is empty.
    //          Throws FileNotFoundException if file doesn't exist.
    //          Throws JSONException if JSON is unreadable by JSONArray constructor.
    // Modifies: this
    public void readFile() throws ReadProfileException, FileNotFoundException {
        Scanner sc = new Scanner(file);
        if (sc.hasNext()) {
            JSONArray jsonArray = new JSONArray(sc.next());
            for (int i = 0; i < jsonArray.length(); i++) {
                savedProfiles.add(readJsonObject(jsonArray.getJSONObject(i)));
            }
        } else {
            throw new ReadProfileException();
        }
    }

    public ArrayList<Profile> getSavedProfiles() {
        return savedProfiles;
    }

    public File getFile() {
        return this.file;
    }
}
