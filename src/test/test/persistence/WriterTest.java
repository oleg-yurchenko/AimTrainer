package test.persistence;

import model.Profile;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.SaveObject;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    Writer writer;
    File testFile;

    @BeforeEach
    public void beforeEach() {
        testFile = new File("./data/testing.json");
        writer = new Writer(testFile);
        if(testFile.delete()) {
            // desired output
        } else {
            System.out.println("unable to delete test file");
        }
    }

    @Test
    public void alternateConstructorTest() {
        writer = new Writer();
        assertEquals(new File(writer.PATH).getName(), writer.getFile().getName());
    }

    @Test
    public void createFileNotExistTest() {
        try {
            writer.createFile();
            Scanner sc = new Scanner(testFile);
        } catch (FileNotFoundException e) {
            fail("file not found");
        } catch (IOException e) {
            fail("unable to create file");
        }
    }

    @Test
    public void createFileExistTest() {
        try {
            assertTrue(testFile.createNewFile());
            writer.createFile();
            assertFalse(testFile.createNewFile());
        } catch (IOException e) {
            fail("Error creating the file");
        }
    }

    @Test
    public void addSaveObjectTest() {
        assertTrue(writer.getSavedObjects().isEmpty());
        writer.addSaveObject(new SaveObject(new Profile("test1")));
        assertTrue(writer.getSavedObjects().length() == 1);
        writer.addSaveObject(new SaveObject(new Profile("test2")));
        assertTrue(writer.getSavedObjects().length() == 2);
    }


    @Test
    public void writeToEmptyFileTest() {
        try {
            testFile.createNewFile();
            Scanner sc = new Scanner(testFile);
            assertFalse(sc.hasNextLine());
            writer.addSaveObject(new SaveObject(new Profile("test")));
            writer.writeToFile();
            sc = new Scanner(testFile);
            assertTrue(sc.hasNextLine());
        } catch (FileNotFoundException e) {
            fail("File not found");
        } catch (IOException e) {
            fail("File could not be created or could not be written to");
        }
    }

    @Test
    public void writeToNotEmptyFileTest() {
        try {
            testFile.createNewFile();
            FileWriter fileWriter = new FileWriter(testFile, true);
            fileWriter.write(new JSONObject(new Profile("test1")).toString());
            fileWriter.close();
            writer.addSaveObject(new SaveObject(new Profile("test2")));
            writer.writeToFile();
        } catch (FileNotFoundException e) {
            fail("File not found");
        } catch (IOException e) {
            fail("File could not be created or could not be written to");
        }
    }

    @Test
    public void clearFileTest() {
        try {
            testFile.createNewFile();
            FileWriter fileWriter = new FileWriter(testFile, true);
            fileWriter.write("test string");
            fileWriter.close();
            Scanner sc = new Scanner(testFile);
            assertTrue(sc.hasNextLine());
            writer.clearFile();
            sc = new Scanner(testFile);
            assertFalse(sc.hasNextLine());
        } catch (FileNotFoundException e) {
            fail("File not found");
        } catch (IOException e) {
            fail("File could not be written to or read to");
        }
    }
}
