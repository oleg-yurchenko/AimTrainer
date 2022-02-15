package persistence;

import exceptions.InvalidJsonException;
import model.Profile;
import org.json.JSONObject;

// The save object that converts a profile to a JSON object and vice versa
public class SaveObject {
    private JSONObject contents;
    private Profile profile;

    // Effects: Creates a new save object with given JSONObject.
    // Modifies: this
    public SaveObject(JSONObject contents) {
        this.contents = contents;
    }

    // Effects: Creates a new save object with a given profile
    // Modifies: this
    public SaveObject(Profile profile) {
        this.profile = profile;
    }

    // Effects: Creates a new profile with the stored contents.
    //          Throws InvalidJsonException if the method cannot make a profile
    //          with the stored contents.
    // Modifies: this
    public void makeProfile() throws InvalidJsonException {
        // stub
    }

    // Effects: Makes a new profile and returns the profile
    //          Throws InvalidJsonException if the method cannot make a profile
    //          with the stored contents.
    public Profile getNewProfile() throws InvalidJsonException {
        return new Profile(""); // stub
    }

    // Effects: Creates and stores a new JSONObject in contents from the stored profile
    // Modifies: this
    public void makeContents() {
        // stub
    }

    // Effects: makes a new JSONObject in contents and returns the contents.
    public JSONObject getNewContents() {
        return new JSONObject();
    }


    public JSONObject getContents() {
        return contents;
    }

    public Profile getProfile() {
        return profile;
    }
}
