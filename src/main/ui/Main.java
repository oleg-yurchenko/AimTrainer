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

public class Main {
    enum Mode {
        GAME,
        PROFILE
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Profile> profiles = new ArrayList<>();

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);

        MenuWindow menuWindow = new MenuWindow(profiles);

        // Open the GUI
        screen.startScreen();

        gui.addWindow(menuWindow);
        menuWindow.waitUntilClosed();

        // Close the GUI
        screen.stopScreen();
    }
}
