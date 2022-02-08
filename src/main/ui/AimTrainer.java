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

public class AimTrainer {
    private ArrayList<Profile> profiles;
    private Terminal terminal;
    private Screen screen;
    private WindowBasedTextGUI gui;
    private MenuWindow menuWindow;


    enum Mode {
        GAME,
        PROFILE
    }

    public AimTrainer() throws IOException {
        profiles = new ArrayList<>();
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        gui =  new MultiWindowTextGUI(screen);
        menuWindow = new MenuWindow(profiles);

        // Open the GUI
        screen.startScreen();

        gui.addWindow(menuWindow);
        menuWindow.waitUntilClosed();

        // Close the GUI
        screen.stopScreen();
    }
}
