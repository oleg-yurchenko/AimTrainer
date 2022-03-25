package ui;

import exceptions.ReadProfileException;
import model.Profile;
import org.json.JSONException;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// AimTrainer represents the structure of the UI. The screen is started here, and all top level variables are held here.
public class AimTrainer extends JFrame {
    private static int frameWidth = 800;
    private static int frameHeight = 600;
    private ArrayList<Profile> profiles;
    private JPanel panel;

    // Represents whether the user wants to play the game or view their profile.
    // Used by ProfileSelectWindow to know which window to open after choosing a profile.
    enum Mode {
        GAME,
        PROFILE
    }

    // Creates and initialized top level variables (such as the frame, profiles list, etc.)
    // Shows the frame and adds the panel as the MenuWindow.
    public AimTrainer() throws IOException {
        super("AimTrainer");
        profiles = new ArrayList<>();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(makeWindowListener());
        setSize(frameWidth, frameHeight);
        setPanel(new MenuWindow(this));
        add(panel);
        setVisible(true);
    }

    // Effects: tries to read the existing file and load the data into the profiles variable.
    //          If ReadProfileException or FileNotFoundException then creates a new file.
    //          If JSONException then rewrites the existing file (JSON was corrupt).
    // Modifies: this, file
    private void loadData() throws IOException {
        try {
            Reader reader = new Reader();
            reader.readFile();
            profiles = reader.getSavedProfiles();
        } catch (ReadProfileException | FileNotFoundException e) {
            System.out.println("profiles.json not found, creating new profiles.json");
            Writer writer = new Writer();
            writer.createFile();
            profiles = new ArrayList<>();
        } catch (JSONException e) {
            System.out.println("Corrupt file, clearing and making new one");
            Writer writer = new Writer();
            writer.clearFile();
            profiles = new ArrayList<>();
        }
    }

    // Effects: Clears the file and stores the current profiles to the file (overwrites the file with new profile list)
    // Modifies: file
    private void saveData() throws IOException {
        Writer writer = new Writer();
        writer.clearFile();
        for (Profile p : profiles) {
            writer.addSaveObject(new SaveObject(p));
        }
        writer.writeToFile();
    }

    // Effects: Asks the user if they'd like to load an existing save file. If they choose yes, the json file is loaded
    //          Otherwise, an empty Profiles list is initialized
    private void askSaveDataLoad() {
        String question = "Would you like to load saved data?";
        if (confirmDialog(question) == JOptionPane.YES_OPTION) {
            try {
                loadData();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Unable to load data");
            }
        }
    }

    // Effects: Asks the user if they'd like to save their data to an existing save file (or create a new one if it
    //          doesn't exist). Otherwise, nothing happens to the JSON file.
    public void exit() {
        String question = "Would you like to save before exiting? (this will overwrite any previous save)";
        if (confirmDialog(question) == JOptionPane.YES_OPTION) {
            try {
                saveData();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Unable to save data");
            }
        }
        System.exit(0);
    }

    // Effects: Shows a dialogue for yes/no prompts with a given question and returns the selected option
    //          0 for yes, 1 for no.
    //          alternatively, JOptionPane.YES_OPTION for yes and JOptionPane.NO_OPTION for no.
    private int confirmDialog(String prompt) {
        return JOptionPane.showConfirmDialog(this, prompt, "", JOptionPane.YES_NO_OPTION);
    }

    // Effects: Creates and returns a new WindowListener that asks the user to load data upon launching the application
    //          and prompting the user to save their data upon exiting.
    private WindowListener makeWindowListener() {
        return new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                askSaveDataLoad();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        };
    }

    public static int getFrameWidth() {
        return frameWidth;
    }

    public static int getFrameHeight() {
        return frameHeight;
    }

    public void setPanel(JPanel targetPanel) {
        setVisible(false);
        if (this.panel != null) {
            remove(this.panel);
        }
        this.panel = targetPanel;
        add(this.panel);
        setVisible(true);
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }
}
