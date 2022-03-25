package model;

// A game mode with shrinking targets
public class ShrinkingGame extends Game {
    public static final float RADIUS_REDUCTION = 0.01f;

    // Effects: Creates a new game with the given profile and time.
    // Modifies: this
    public ShrinkingGame(Profile profile, int time) {
        super(profile, time, "black");
    }

    // Effects: In addition to the super's tick function, the radius is decreased by a constant amount.
    //          If the radius is leq 0.0f, the game registers a miss and creates a new target.
    // Modifies: this, profile, target
    @Override
    public void tick() {
        super.tick();
        target.changeRadius(-RADIUS_REDUCTION);
        if (target.getRadius() <= 0.0f) {
            profile.click();
            target = makeTarget(START_RADIUS);
        }
    }

    // Effects: Gets a new target. If the radius of the new target is not equal to the starting radius (due to the way
    //          the tick() function works), set the radius to the start radius and return the target.
    // Modifies: tempTarget
    // Requires: radius >= 0.0f
    @Override
    public Target makeTarget(float radius) {
        Target tempTarget = super.makeTarget(radius);
        if (tempTarget.getRadius() != START_RADIUS) {
            tempTarget.setRadius(START_RADIUS);
        }
        return tempTarget;
    }
}
