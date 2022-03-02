package test.persistence;

import exceptions.ReadProfileException;
import model.Profile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.SaveObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    Reader reader;
    File testFile;

    @BeforeEach
    public void beforeEach() {
        testFile = new File("./data/testing.json");
        reader = new Reader(testFile); // set it to our default testFile rather than the production's data file
        try {
            if (testFile.createNewFile()) {
                // File is created
            } else {
                // File exists, erase contents
                new FileWriter(testFile, false).close(); // erases the file's contents and closes it.
            }
        } catch (IOException e) {
            fail("Unable to make test file"); // this should never happen
        }
    }

    @Test
    public void alternateConstructorTest() {
        reader = new Reader();
        assertEquals(new File(reader.PATH).getName(), reader.getFile().getName());
    }

    @Test
    public void readJsonObjectTest() {
        try {
            assertEquals(new Profile("test").getName(), reader.readJsonObject(new JSONObject(new Profile("test"))).getName());
        } catch (JSONException e) {
            fail("Couldn't read the given JSONObject");
        }

        try {
            reader.readJsonObject(new JSONObject("{}"));
            fail("This JSONObject should not be possible to read by this method");
        } catch (JSONException e) {
            // expected
        }
    }

    @Test
    public void readFileEmptyTest() {
        try {
            reader.readFile();
        } catch (ReadProfileException e) {
            // should exit here
        } catch (FileNotFoundException e) {
            fail("Couldn't find the file");
        }
    }

    @Test
    public void readFileOneProfileTest() {
        try {
            assertEquals(0, reader.getSavedProfiles().size());
            FileWriter fileWriter = new FileWriter(testFile, false);
            JSONArray desiredArray = new JSONArray();
            desiredArray.put(new SaveObject(new Profile("test")).getNewContents());
            fileWriter.write(desiredArray.toString());
            fileWriter.close();
            reader.readFile();
            assertEquals(1, reader.getSavedProfiles().size());
        } catch (IOException e) {
            fail("Error writing to the file");
        } catch (ReadProfileException e) {
            fail("Couldn't read the file");
        }
    }

    @Test
    public void readFileTwoProfileTest() {
        try {
            assertEquals(0, reader.getSavedProfiles().size());
            FileWriter fileWriter = new FileWriter(testFile, false);
            ArrayList<Profile> profiles = new ArrayList<>();
            profiles.add(new Profile("test1"));
            profiles.add(new Profile("test2"));
            JSONArray desiredArray = new JSONArray();
            for (Profile p : profiles) {
                desiredArray.put(new SaveObject(p).getNewContents());
            }
            fileWriter.write(desiredArray.toString());
            fileWriter.close();
            reader.readFile();
            assertEquals(2, reader.getSavedProfiles().size());
            assertEquals(profiles.get(0).getName(), reader.getSavedProfiles().get(0).getName());
            assertEquals(profiles.get(1).getName(), reader.getSavedProfiles().get(1).getName());
        } catch (IOException e) {
            fail("Error writing to the file");
        } catch (ReadProfileException e) {
            fail("Couldn't read the file");
        }
    }
}
