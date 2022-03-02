package persistence;

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
    public void makeProfile() {
        String name = this.contents.getString("name");
        int hits = this.contents.getInt("hits");
        int clicks = this.contents.getInt("clicks");
        float sumOfDistances = this.contents.getFloat("sumOfDistances");
        this.profile = new Profile(name, hits, clicks, sumOfDistances);
    }

    // Effects: Makes a new profile and returns the profile
    //          Throws InvalidJsonException if the method cannot make a profile
    //          with the stored contents.
    public Profile getNewProfile() {
        makeProfile();
        return this.profile;
    }

    // Effects: Creates and stores a new JSONObject in contents from the stored profile
    // Modifies: this
    public void makeContents() {
        this.contents = new JSONObject(this.profile);
    }

    // Effects: makes a new JSONObject in contents and returns the contents.
    public JSONObject getNewContents() {
        makeContents();
        return this.contents;
    }


    public JSONObject getContents() {
        return contents;
    }

    public Profile getProfile() {
        return profile;
    }
}
