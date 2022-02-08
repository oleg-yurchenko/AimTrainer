package ui;

import com.googlecode.lanterna.gui2.*;
import model.Profile;

import java.util.ArrayList;
import java.util.HashSet;

public class MenuWindow extends BasicWindow {
    private ProfileSelectWindow profileSelectWindow;
    private ArrayList<Profile> profiles;

    public MenuWindow(ArrayList<Profile> profileList) {
        super("Menu");
        this.profiles = profileList;

        HashSet<Hint> defaultHints = new HashSet<>();
        defaultHints.add(Hint.FULL_SCREEN);
        this.setHints(defaultHints);

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Button("Play", new Runnable() {
            @Override
            public void run() {
                profileSelectWindow = new ProfileSelectWindow(profiles, AimTrainer.Mode.GAME);
                getTextGUI().addWindow(profileSelectWindow);
                profileSelectWindow.waitUntilClosed();
            }
        }));

        panel.addComponent(new Button("Profiles", new Runnable() {
            @Override
            public void run() {
                profileSelectWindow = new ProfileSelectWindow(profiles, AimTrainer.Mode.PROFILE);
                getTextGUI().addWindow(profileSelectWindow);
                profileSelectWindow.waitUntilClosed();
            }
        }));

        panel.addComponent(new Button("Quit", new Runnable() {
            @Override
            public void run() {
                MenuWindow.this.close();
            }
        }));

        setComponent(panel);
    }

}
