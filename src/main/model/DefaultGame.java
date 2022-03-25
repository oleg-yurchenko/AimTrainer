package model;

// The default game mode (just timed)
public class DefaultGame extends Game {

    // Effects: creates a game with the given profile, time limit, and colour of the targets
    // Modifies: this
    public DefaultGame(Profile profile, int time) {
        super(profile, time, "black");
    }
}
