package ui;


import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Profile;

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
        // Stores the profiles during the entire running time of the game
        profiles = new ArrayList<>();
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
    }
}
