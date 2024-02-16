package ui;

import model.Mood;
import model.MoodList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// MoodTracker Application
public class MoodTracker {
    private Scanner input;
    private MoodList moodList;

    // EFFECTS: runs the mood tracker app
    public MoodTracker() {
        runMoodTracker();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void runMoodTracker() {
        boolean programOngoing = true;
        String answer;

        initializeTracker();
        welcomeMessage();
        answer = input.next();
        answer = answer.toLowerCase();

        if (answer.equals("y")) {
            addToList();
        }

        while (programOngoing) {
            System.out.println("\n");
            openMenu();
            answer = input.next();
            System.out.println("\n");
            if (!processAnswer(answer)) {
                programOngoing = false;
            }
        }
        System.out.println("Until next time.");
    }

    //EFFECTS: list of operations that are operated on moodList
    private boolean processAnswer(String answer) {
        switch (answer) {
            case "q": return false;
            case "vl":
                viewList();
                return true;
            case "al":
                addToList();
                return true;
            case "del":
                deleteFromList();
                return true;
            case "vn":
                viewNote();
                return true;
            case "en":
                editMood();
                return true;
            case "fl":
                filterList();
                return true;
            default:
                return true;
        }
    }

    //REQUIRE: moodID exists in the moodList
    //MODIFY: this
    //EFFECTS: deletes a mood from the moodList base on user input moodID
    private void deleteFromList() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        moodList.deleteMood(id);
        System.out.println(id + " deleted from the list!");
        enterToContinue();
    }

    //REQUIRE: date to be valid, moodTag has proper entry
    //MODIFY: this, mood
    //EFFECTS: add new mood entry to moodList
    private void addToList() {
        System.out.println("Please enter date of entry as year, month, date:");
        System.out.println("Year:");
        int year = Integer.parseInt(input.next());
        System.out.println("Month:");
        int month = Integer.parseInt(input.next());
        System.out.println("Day:");
        int day = Integer.parseInt(input.next());
        System.out.println("Please enter your mood:");
        System.out.println("For example: Happy");
        String mood = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String moodTag = input.next();
        System.out.println("Please enter any notes for this entry:");
        String note = input.next();
        LocalDate date = LocalDate.of(year, month, day);
        Mood moodObject = new Mood(moodList.getMoodID(), date, mood, moodTag, note);
        moodList.addMood(moodObject);
        System.out.println("New entry added!");
        enterToContinue();
    }

    //EFFECTS: view the list of moods in MoodList by their ID, Date, and Color
    private void viewList() {
        for (Mood m: moodList.getMoodList()) {
            System.out.println("ID:" + m.getID() + " Date:" + m.getDate().toString() + " Color:" + m.getMoodColor());
        }
        enterToContinue();
    }

    //EFFECTS: view the note of a mood associated to a user input moodID
    private void viewNote() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        String note = moodList.findMood(id).getNote();
        System.out.println("Note ID " + id + ": " + note);
        enterToContinue();
    }

    //MODIFIES: mood
    //EFFECTS: user choose mood and what type of edits mood will be operated on
    private void editMood() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        Mood mood = moodList.findMood(id);
        System.out.println("What would you like to edit?");
        System.out.println("d -> date");
        System.out.println("m -> mood");
        System.out.println("n -> notes");
        System.out.println("all -> all of the above");
        String answer = input.next();
        processEditMoodAnswer(mood, answer);
        enterToContinue();
    }

    //MODIFIES: mood
    //EFFECTS: select which type of edits mood is worked on
    private void processEditMoodAnswer(Mood mood, String answer) {
        switch (answer) {
            case "d":
                editMoodDate(mood);
                break;
            case "m":
                editMoodOfOldMood(mood);
                break;
            case "n":
                editMoodNote(mood);
                break;
            case "all":
                editMoodAll(mood);
                break;
        }
    }

    //REQUIRES: Valid date on calendar
    //MODIFIES: mood
    //EFFECTS: replaces old Mood and all its parameters with new Mood with user inputs
    private void editMoodAll(Mood mood) {
        System.out.println("Re-editing mood...");
        System.out.println("Please enter date of entry as year, month, date:");
        System.out.println("Year:");
        int year = Integer.parseInt(input.next());
        System.out.println("Month:");
        int month = Integer.parseInt(input.next());
        System.out.println("Day:");
        int day = Integer.parseInt(input.next());
        System.out.println("Please enter your mood:");
        System.out.println("For example: Happy");
        String moodText = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String moodTag = input.next();
        System.out.println("Please enter any notes for this entry:");
        String note = input.next();
        LocalDate date = LocalDate.of(year, month, day);
        mood.setDate(date);
        mood.setMood(moodText, moodTag);
        mood.setNote(note);
    }

    //MODIFIES: mood
    //EFFECTS: in input replace, replaces old note with user input note; if input add, adds the new note
    // on top of the old one
    private void editMoodNote(Mood mood) {
        System.out.println("Would you like to replace or add onto new note? Enter replace or add:");
        String replaceOrAdd = input.next();
        replaceOrAdd = replaceOrAdd.toLowerCase();
        if (replaceOrAdd.equals("replace")) {
            System.out.println("Whats your new note?");
            String newNote = input.next();
            mood.setNote(newNote);
            System.out.println(newNote + "is now the new note.");
        } else {
            System.out.println("What would you like to add?");
            String addNote = input.next();
            mood.addNote(addNote);
            System.out.println(addNote + "added.");
        }
    }

    //MODIFIES: mood
    //EFFECTS: replaces old mood and moodTag with new ones input by user
    private void editMoodOfOldMood(Mood mood) {
        System.out.println("What is your new mood?");
        String newMood = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String newMoodTag = input.next();
        mood.setMood(newMood, newMoodTag);
        System.out.println("Replaced old mood with " + newMood + "with " + newMoodTag + "as tag.");
    }

    //REQUIRES: Valid date on calendar
    //MODIFIES: mood
    //EFFECTS: replaces old mood date with newly user input date in year, month, day
    private void editMoodDate(Mood mood) {
        System.out.println("Editing date...");
        System.out.println("Please enter date of entry as year, month, date:");
        System.out.println("Year:");
        int year = Integer.parseInt(input.next());
        System.out.println("Month:");
        int month = Integer.parseInt(input.next());
        System.out.println("Day:");
        int day = Integer.parseInt(input.next());
        LocalDate date = LocalDate.of(year, month, day);
        mood.setDate(date);
        System.out.println("The new date is: " + date);
    }

    //EFFECTS: makes a new filtered list within the method filtered by input methodTag by user
    private void filterList() {
        System.out.println("Which tag would you like to filter the list with?");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String filterTag = input.next();
        ArrayList<Mood> filteredMoods = new ArrayList<>();
        for (Mood m : moodList.getMoodList()) {
            if (m.getMoodTag().equals(filterTag)) {
                filteredMoods.add(m);
                System.out.println("ID:" + m.getID() + " Date:" + m.getDate().toString() + "Color:" + m.getMoodColor());
            }
        }
        if (filteredMoods.isEmpty()) {
            System.out.println("Nothing " + filterTag + " in this list!");
        }
        enterToContinue();
    }

    //EFFECTS: main menu for user
    private void openMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("vl -> view list");
        System.out.println("al -> add to list");
        System.out.println("del -> delete from list");
        System.out.println("vn -> view note");
        System.out.println("en -> edit note");
        System.out.println("fl -> filter list");
        System.out.println("q -> quit");
    }

    //EFFECTS: initialize MoodList
    private void initializeTracker() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        moodList = new MoodList();
    }

    //EFFECTS: welcome message after initial launch
    private void welcomeMessage() {
        System.out.println("\nWelcome to the Mood Tracker!");
        System.out.println("\nWould you like to make an entry? (Y/N)");
    }

    //EFFECTS: press a key to continue program
    private void enterToContinue() {
        System.out.println("Press Enter to continue");
        input.next();
    }
}
