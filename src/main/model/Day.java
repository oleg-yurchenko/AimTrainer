package model;

public class Day {
    private int caloriesGained;
    private int caloriesBurned;

    // Effects: sets initial values to 0;
    // Modifies: this;
    public Day() {
        this.caloriesGained = 0;
        this.caloriesBurned = 0;
    }

    // Effects: adds the given amount of calories to caloriesGained;
    // Modifies: this;
    // Requires: calories > 0;
    public void gainCalories(int calories) {
        this.caloriesGained += calories;
    }

    // Effects: adds the given amount of calories to caloriesBurned;
    // Modifies: this;
    // Requires: calories > 0;
    public void burnCalories(int calories) {
        this.caloriesBurned += calories;
    }

    public int getCaloriesGained() {
        return this.caloriesGained;
    }

    public int getCaloriesBurned() {
        return this.caloriesBurned;
    }

    // Effects: produces the difference between the calories gained and lost;
    public int calorieDifference() {
        return this.caloriesGained - this.caloriesBurned;
    }
}
