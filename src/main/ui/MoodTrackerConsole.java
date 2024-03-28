package ui;

import model.Mood;
import model.MoodList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// MoodTracker Application
public class MoodTrackerConsole {
    private static final String JSON_LOCATION = "./data/moodlist.json";
    private Scanner input;
    private MoodList moodList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the mood tracker app
    public MoodTrackerConsole() {
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
            if (answer.equals("q")) {
                programOngoing = false;
            } else {
                processAnswer(answer);
            }
        }
        System.out.println("Until next time.");
    }

    //MODIFIES: this
    //EFFECTS: initialize moodList and input
    private void initializeTracker() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        moodList = new MoodList();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
    }

    //EFFECTS: welcome message after initial launch
    private void welcomeMessage() {
        System.out.println("\nWelcome to the Mood Tracker!");
        System.out.println("\nWould you like to make an entry? (Y/N)");
    }

    //EFFECTS: print main menu for user
    private void openMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("vl -> view list");
        System.out.println("al -> add to list");
        System.out.println("del -> delete from list");
        System.out.println("vn -> view note");
        System.out.println("en -> edit note");
        System.out.println("fl -> filter list");
        System.out.println("s -> save list");
        System.out.println("l -> load list");
        System.out.println("q -> quit");
    }

    //EFFECTS: list of operations that are operated on moodList, only return false
    // when answer is q, true otherwise
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void processAnswer(String answer) {
        switch (answer) {
            case "vl":
                viewList();
                break;
            case "al":
                addToList();
                break;
            case "del":
                deleteFromList();
                break;
            case "vn":
                viewNote();
                break;
            case "en":
                editMood();
                break;
            case "fl":
                filterList();
                break;
            case "s":
                saveList();
                break;
            case "l":
                loadList();
                break;
            default:
                break;
        }
    }

    // REQUIRE: getLocalDate() is a valid LocalDate
    // MODIFY: this, Mood
    // EFFECTS: add new Mood entry to moodList
    private void addToList() {
        LocalDate date = getLocalDate();
        System.out.println("Please enter your mood:");
        System.out.println("For example: Happy");
        String mood = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String moodTag = input.next();
        System.out.println("Please enter any notes for this entry:");
        String note = input.next();
        Mood moodObject = new Mood(moodList.getMoodID(), date, mood, moodTag, note);
        moodList.addMood(moodObject);
        System.out.println("New entry added!");
        enterToContinue();
    }

    // EFFECTS: view the list of moods in moodList by their ID, Date, and mood; color set by corresponding moodTag
    private void viewList() {
        if (!(moodList.getMoodList().isEmpty())) {
            for (Mood m: moodList.getMoodList()) {
                System.out.print(m.getAnsiColor());
                System.out.printf("\nID:%d Date:%s Mood:%s", m.getID(), m.getDate().toString(), m.getMood());
                System.out.print("\033[0m");
            }
        } else {
            System.out.println("No entries found!");
        }
        enterToContinue();
    }

    // MODIFY: this
    // EFFECTS: deletes a mood from the moodList base on user input entryID
    private void deleteFromList() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        if (!(moodList.findMood(id) == null)) {
            moodList.deleteMood(id);
            System.out.println("ID:" + id + " deleted from the list!");
        } else {
            System.out.println("ID does not exist in this mood list!");
        }
        enterToContinue();
    }

    // MODIFIES: this
    // EFFECTS: view the note of a mood associated to a user input entryID
    private void viewNote() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        if (!(moodList.findMood(id) == null)) {
            System.out.println("Note ID " + id + ": " + moodList.findMood(id).getNote());
        } else {
            System.out.println("ID does not exist in this mood list!");
        }
        enterToContinue();
    }

    // MODIFIES: this
    // EFFECTS: user choose entryID and what type of edits mood will be operated on
    private void editMood() {
        System.out.println("Please enter the mood ID:");
        int id = Integer.parseInt(input.next());
        if (!(moodList.findMood(id) == null)) {
            Mood mood = moodList.findMood(id);
            System.out.println("What would you like to edit?");
            System.out.println("d -> date");
            System.out.println("m -> mood");
            System.out.println("n -> notes");
            System.out.println("all -> all of the above");
            String answer = input.next();
            processEditMoodAnswer(mood, answer);
        } else {
            System.out.println("ID does not exist in this mood list!");
        }
        enterToContinue();
    }

    // EFFECTS: select which type of edits mood is worked on
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
            default:
                System.out.println("Invalid input, returning to menu.");
        }
    }

    // REQUIRES: getLocalDate() is a valid LocalDate
    // MODIFIES: this, mood
    // EFFECTS: replaces old Mood and all its parameters with new Mood with user inputs
    private void editMoodAll(Mood mood) {
        System.out.println("Re-editing mood...");
        mood.setDate(getLocalDate());
        System.out.println("Please enter your mood:");
        System.out.println("For example: Happy");
        String moodText = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String moodTag = input.next();
        mood.setMood(moodText, moodTag);
        System.out.println("Please enter any notes for this entry:");
        String note = input.next();
        mood.setNote(note);
        System.out.println("ID " + mood.getID() + " is now fully updated.");
    }

    // MODIFIES: this, mood
    // EFFECTS: in input "replace", replaces old note with user input note; if input "add", adds the new note
    // on top of the old one
    private void editMoodNote(Mood mood) {
        System.out.println("Would you like to replace or add onto new note? Enter replace or add:");
        String replaceOrAdd = input.next();
        replaceOrAdd = replaceOrAdd.toLowerCase();
        if (replaceOrAdd.equals("replace")) {
            System.out.println("Whats your new note?");
            String newNote = input.next();
            mood.setNote(newNote);
            System.out.println("This is now the new note for ID " + mood.getID() + ": " + mood.getNote());
        } else {
            System.out.println("What would you like to add?");
            String addNote = input.next();
            mood.addNote(addNote);
            System.out.println("Added: " + addNote);
        }
    }

    // MODIFIES: this, mood
    // EFFECTS: replaces old mood and moodTag with new ones input by user
    private void editMoodOfOldMood(Mood mood) {
        System.out.println("What is your new mood?");
        String newMood = input.next();
        System.out.println("Please select the tag best describe to your mood from list below:");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String newMoodTag = input.next();
        mood.setMood(newMood, newMoodTag);
        System.out.println("Replaced old mood with " + newMood + " and " + newMoodTag + " as tag.");
    }

    // REQUIRES: getLocalDate() is a valid LocalDate
    // MODIFIES: mood
    // EFFECTS: replaces old mood date with newly user defined LocalDate
    private void editMoodDate(Mood mood) {
        System.out.println("Editing date...");
        LocalDate date = getLocalDate();
        mood.setDate(date);
        System.out.println("The new date is: " + date);
    }

    // REQUIRES: input.next() == ("Positive" || "Positive Neutral" || "Neutral" || "Negative Neutral" || "Negative")
    // MODIFIES: this
    // EFFECTS: makes a new filtered list within the method filtered by input methodTag by user
    private void filterList() {
        System.out.println("Which tag would you like to filter the list with?");
        System.out.println("Positive, Positive Neutral, Neutral, Negative Neutral, Negative");
        String filterTag = input.next();
        if (moodList.filterMoodByTag(filterTag).isEmpty()) {
            System.out.println("Nothing " + filterTag + " in this list!");
        } else {
            ArrayList<Mood> filteredMoods = moodList.filterMoodByTag(filterTag);
            for (Mood m : filteredMoods) {
                System.out.print(m.getAnsiColor());
                System.out.printf("\nID:%d Date:%s", m.getID(), m.getDate().toString());
                System.out.print("\033[0m");
            }
        }
        enterToContinue();
    }

    // EFFECTS: saves all mood entries to file
    private void saveList() {
        try {
            jsonWriter.open();
            jsonWriter.write(moodList);
            jsonWriter.close();
            System.out.println("Saved your mood entries to " + JSON_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
        }
        enterToContinue();
    }

    // MODIFIES: this
    // EFFECTS: loads all mood entries from MoodList from file
    private void loadList() {
        try {
            moodList = jsonReader.read();
            System.out.println("Loaded previous save from " + JSON_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
        }
        enterToContinue();
    }

    // REQUIRES: valid year, month, day values for LocalDate
    // MODIFIES: this
    // EFFECTS: from user input year, month, day values, return a LocalDate
    private LocalDate getLocalDate() {
        System.out.println("Please enter date of entry as year, month, date:");
        System.out.println("Year:");
        int year = Integer.parseInt(input.next());
        System.out.println("Month:");
        int month = Integer.parseInt(input.next());
        System.out.println("Day:");
        int day = Integer.parseInt(input.next());
        return LocalDate.of(year, month, day);
    }

    // EFFECTS: press enter to continue program
    private void enterToContinue() {
        System.out.println("\nPress Enter to continue");
        input.next();
    }
}
