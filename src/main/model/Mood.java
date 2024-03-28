package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.time.LocalDate;

// Represents a mood entry, including the date, mood, moodTag and note associated to said entry
public class Mood implements Writable {
    // constants for colors associated to moodTag
    private static final Color positiveColor = new Color(82, 228, 117);
    private static final Color positiveNeutralColor = new Color(169, 236, 185);
    private static final Color neutralColor = new Color(222, 218, 218);
    private static final Color negativeNeutralColor = new Color(245, 185, 185);
    private static final Color negativeColor = new Color(239, 144, 148);
    private static final Color defaultColor = new Color(211, 211, 211);

    private final int entryID;
    private LocalDate date;     // date of entry
    private String mood;        // mood of said entry
    private String moodTag;     // tag associated to mood
    private Color color;        // color associated to moodTag
    private String ansiColor;   // ansi color associated to moodTag
    private String note;        // additional notes for said entry

    // REQUIRES: valid date entry
    // EFFECTS: initializes the mood entry with given inputs; moodTag determines color for this entry
    public Mood(int id, LocalDate date, String mood, String moodTag, String note) {
        this.entryID = id;
        this.date = date;
        this.mood = mood;
        this.moodTag = moodTag;
        this.note = note;
        setColor(moodTag);
        setAnsiColor(moodTag);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getMood() {
        return this.mood;
    }

    public String getMoodTag() {
        return this.moodTag;
    }

    public Color getMoodColor() {
        return this.color;
    }

    public String getAnsiColor() {
        return this.ansiColor;
    }

    public String getNote() {
        return this.note;
    }

    public int getID() {
        return this.entryID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMood(String mood, String moodTag) {
        this.mood = mood;
        this.moodTag = moodTag;
        setColor(moodTag);
        setAnsiColor(moodTag);
    }

    public void setNote(String note) {
        this.note = note;
    }

    //MODIFIES: this
    //EFFECTS: add current note on top of input note
    public void addNote(String note) {
        this.note = this.note + " " + note;
    }

    //REQUIRES: moodTag to be "Positive", "Positive Neutral", "Neutral", "Negative", "Negative Neutral"
    //MODIFIES: this
    //EFFECTS: set mood color according to moodTag
    private void setColor(String moodTag) {
        switch (moodTag) {
            case "Positive":
                this.color = positiveColor;
                break;
            case "Positive Neutral":
                this.color = positiveNeutralColor;
                break;
            case "Neutral":
                this.color = neutralColor;
                break;
            case "Negative":
                this.color = negativeColor;
                break;
            case "Negative Neutral":
                this.color = negativeNeutralColor;
                break;
            default:
                this.color = defaultColor;
        }
    }

    //REQUIRES: moodTag to be "Positive", "Positive Neutral", "Neutral", "Negative", "Negative Neutral"
    //MODIFIES: this
    //EFFECTS: set mood ansi color according to moodTag
    private void setAnsiColor(String moodTag) {
        switch (moodTag) {
            case "Positive":
                this.ansiColor = "\u001b[38;5;34m";
                break;
            case "Positive Neutral":
                this.ansiColor = "\u001b[38;5;10m";
                break;
            case "Neutral":
                this.ansiColor = "\u001b[0m";
                break;
            case "Negative Neutral":
                this.ansiColor = "\u001b[38;5;9m";
                break;
            case "Negative":
                this.ansiColor = "\u001b[38;5;160m";
                break;
            default:
                this.ansiColor = "\u001b[37m";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entryID", entryID);
        json.put("date", date);
        json.put("mood", mood);
        json.put("moodTag", moodTag);
        json.put("note", note);
        return json;
    }
}
