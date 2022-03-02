package test.persistence;

import model.Profile;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.SaveObject;

import static org.junit.jupiter.api.Assertions.*;

public class SaveObjectTest {
    SaveObject saveObjectJson;
    SaveObject saveObjectProfile;

    @BeforeEach
    public void beforeEach() {
        saveObjectJson = new SaveObject(new JSONObject(new Profile("json")));
        saveObjectProfile = new SaveObject(new Profile("profile"));
    }

    @Test
    public void makeProfileTest() {
        saveObjectJson.makeProfile();
        Profile savedProfile = saveObjectJson.getProfile();
        Profile desiredProfile = new Profile("json");
        assertEquals(savedProfile.getName(), desiredProfile.getName());
        assertEquals(savedProfile.getClicks(), desiredProfile.getClicks());
        assertEquals(savedProfile.getHits(), desiredProfile.getHits());
        assertEquals(savedProfile.getSumOfDistances(), desiredProfile.getSumOfDistances());
    }

    @Test
    public void getNewProfileTest() {
        Profile savedProfile = saveObjectJson.getNewProfile();
        Profile desiredProfile = new Profile("json");
        assertEquals(savedProfile.getName(), desiredProfile.getName());
        assertEquals(savedProfile.getClicks(), desiredProfile.getClicks());
        assertEquals(savedProfile.getHits(), desiredProfile.getHits());
        assertEquals(savedProfile.getSumOfDistances(), desiredProfile.getSumOfDistances());
    }

    @Test
    public void makeContentsTest() {
        saveObjectProfile.makeContents();
        assertEquals(new JSONObject(new Profile("profile")).toString(), saveObjectProfile.getContents().toString());
    }

    @Test
    public void getNewContentsTest() {
        assertEquals(new JSONObject(new Profile("profile")).toString(), saveObjectProfile.getNewContents().toString());
    }
}
