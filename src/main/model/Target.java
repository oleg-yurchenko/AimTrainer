package model;

// Represents a target that the user will try to click during the game. It has an x and y position, a colour and radius.
public class Target {
    // not sure how the GUI will process coordinates, float or int, but keep it as float for now
    private float posX;
    private float posY;
    private String colour;
    private float radius;

    // Effects: makes a new target with given x and y positions, given colour (hex code) and radius.
    // Modifies: this
    public Target(float x, float y, String colour, float radius) {
        this.posX = x;
        this.posY = y;
        this.colour = colour;
        this.radius = radius;
    }

    // Effects: moves the x value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveX(float amount) {
        this.posX += amount;
    }

    // Effects: moves the y value by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    public void moveY(float amount) {
        this.posY += amount;
    }

    // Effects: changes the colour of the target to the given colour (hex code)
    // Modifies: this
    // Requires: colour is in hex (Integer.toHexString() was used, or custom hex value)
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Effects: modifies the radius of the circle by the given amount
    // Modifies: this
    // Requires: !(amount == 0)
    //           this.radius - amount >= 0
    public void changeRadius(float amount) {
        this.radius += amount;
    }

    // Effects: gets the distance from the center of the target to the given point
    public float getDistanceToPoint(float x, float y) {
        float distX = x - posX;
        float distY = y - posY;
        return (float) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)); // don't really need the exact precision
    }

    // Effects: returns true if the given coordinates are inside the target
    public boolean isHit(float x, float y) {
        return getDistanceToPoint(x, y) <= radius;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public String getColour() {
        return colour;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}