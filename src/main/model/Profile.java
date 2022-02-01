package model;

import java.util.ArrayList;

public class Profile {
    private ArrayList<Day> days;
    private String name;

    // Effects: produces a new Profile with an empty day list and a given name;
    // Modifies: this;
    public Profile(String name) {
        this.days = new ArrayList<>();
        this.name = name;
    }

    // Effects: Adds a new day to the days list;
    // Modifies: this;
    public void addDay() {
        this.days.add(new Day());
    }

    public int getNumDays() {
        return this.days.size();
    }

    public String getName() {
        return this.name;
    }

    public Day getToday() {
        return days.get(days.size() - 1);
    }
}
