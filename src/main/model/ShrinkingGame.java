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
    public Target makeTarget(float radius) {
        Target tempTarget = super.makeTarget(radius);
        if (tempTarget.getRadius() != START_RADIUS) {
            tempTarget.setRadius(START_RADIUS);
        }
        return tempTarget;
    }
}
