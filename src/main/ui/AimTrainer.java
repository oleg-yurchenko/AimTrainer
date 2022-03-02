package ui;


import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import exceptions.ReadProfileException;
import model.Profile;
import org.json.JSONException;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// AimTrainer represents the structure of the UI. The screen is started here, and all top level variables are held here.
public class AimTrainer {
    private ArrayList<Profile> profiles;
    private Terminal terminal;
    private Screen screen;
    private WindowBasedTextGUI gui;
    private MenuWindow menuWindow;

    // Represents whether the user wants to play the game or view their profile.
    // Used by ProfileSelectWindow to know which window to open after choosing a profile.
    enum Mode {
        GAME,
        PROFILE
    }

    // Creates all top level variables (profiles, terminal, screen, gui) and displays the main menu window.
    public AimTrainer() throws IOException {
        // Read profiles from data. If it doesn't exist, make a new file.
        loadData();
        // Top level declaration for the user interface display
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        gui = new MultiWindowTextGUI(screen);
        // The menu window, which the user will be shown upon launching the game
        menuWindow = new MenuWindow(profiles);

        // Open the GUI
        screen.startScreen();

        gui.addWindow(menuWindow);
        menuWindow.waitUntilClosed();

        // Close the GUI
        screen.stopScreen();

        // Save profiles to profiles.json
        saveData();
    }

    // Effects: tries to read the existing file and load the data into the profiles variable.
    //          If ReadProfileException or FileNotFoundException then creates a new file.
    //          If JSONException then rewrites the existing file (JSON was corrupt).
    // Modifies: this, File file
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
    // Modifies: File file
    private void saveData() throws IOException {
        Writer writer = new Writer();
        writer.clearFile();
        for (Profile p : profiles) {
            writer.addSaveObject(new SaveObject(p));
        }
        writer.writeToFile();
    }
}
