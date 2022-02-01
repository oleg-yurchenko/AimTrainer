package ui;

import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public enum AppState {
        PROFILES,
        DAYS,
        TRACKING,
        QUIT
    }

    private static ArrayList<Profile>  profiles;
    private static AppState appState;
    private static Scanner sc;
    private static Profile selectedProfile;

    public static void main(String[] args) {
        profiles = new ArrayList<>();
        appState =  AppState.PROFILES;
        sc = new Scanner(System.in);
        while (!appState.equals(AppState.QUIT)) {
            switch (appState) {
                case PROFILES:
                    profileSelector();
                    break;
                case DAYS:
                    daySelector();
                    break;
                case TRACKING:
                    trackingCalories();
                    break;
                default: appState = AppState.QUIT;
            }
        }
        System.out.println("Quitting...");
    }

    public static void profileSelector() {
        for (int i = 0; i < profiles.size(); i++) {
            System.out.println(i + " " + profiles.get(i).getName());
        }
        System.out.println(profiles.size() + " to create a new profile");
        System.out.println("-1 to quit the program");
        int input;
        try { // temporary error catcher (very primitive)
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("I'm sorry, I didn't understand the input...");
            appState = AppState.QUIT;
            return;
        }
        if (input == profiles.size()) {
            System.out.println("Enter the profile's name");
            sc.nextLine(); // skip the newline character after the integer
            String newName = sc.nextLine();
            profiles.add(new Profile(newName));
        } else if (input == -1) {
            appState = AppState.QUIT;
        } else {
            selectedProfile = profiles.get(input);
            appState = AppState.DAYS;
        }
    }

    public static void daySelector() {
        System.out.println("Enter 1 to edit today's calories");
        System.out.println("Enter 2 to start a new day");
        System.out.println("Enter 3 to go back to the profile selector");
        System.out.println("Anything else to quit");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                if (selectedProfile.getNumDays() != 0) {
                    appState = AppState.TRACKING;
                } else {
                    System.out.println("Sorry, but you don't have any days on this profile");
                }
                break;
            case 2:
                selectedProfile.addDay();
                appState = AppState.TRACKING;
                break;
            case 3:
                appState = AppState.PROFILES;
                break;
            default:
                appState = AppState.QUIT;
        }

    }

    public static void trackingCalories() {
        while (true) {
            System.out.println("Enter the amount of calories");
            System.out.println("(Negative values to decrease calories, positive to increase)");
            System.out.println("Enter 0 calories to go back to the day selector");
            int inputCalories = sc.nextInt();
            if (inputCalories > 0) {
                selectedProfile.getToday().gainCalories(inputCalories);
            } else if (inputCalories < 0) {
                selectedProfile.getToday().burnCalories(-inputCalories);
            } else {
                int calorieDiff = selectedProfile.getToday().calorieDifference();
                if (calorieDiff > 0) {
                    System.out.println("You gained " + calorieDiff + " calories today");
                } else if (calorieDiff < 0) {
                    System.out.println("You lost " + calorieDiff + " calories today");
                } else {
                    System.out.println("Wow, you maintained complete balance! Your calorie difference was 0!");
                }
                appState = AppState.DAYS;
                break;
            }
        }
    }

}
