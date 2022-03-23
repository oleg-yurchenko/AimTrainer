package model;

public class ShrinkingGame extends Game {
    public static final float RADIUS_REDUCTION = 0.01f;

    public ShrinkingGame(Profile profile, int time) {
        super(profile, time, "black");
    }

    @Override
    public void tick() {
        super.tick();
        target.changeRadius(-RADIUS_REDUCTION);
        if (target.getRadius() <= 0.0f) {
            profile.click();
            target = makeTarget(START_RADIUS);
        }
    }

    @Override
    public void onClick(int cursorX, int cursorY) {
        if (target.getRadius() != START_RADIUS) {
            target.setRadius(START_RADIUS);
        }
        super.onClick(cursorX, cursorY);
    }
}
