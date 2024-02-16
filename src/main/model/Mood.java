package model;

import java.awt.*;
import java.time.LocalDate;

// Represents a mood entry, including the date, mood, moodTag and note associated to said entry
public class Mood {
    // constants for colors associated to moodTag
    private static final Color positiveColor = new Color(0, 204, 0);
    private static final Color positiveNeutralColor = new Color(102, 255, 102);
    private static final Color neutralColor = new Color(255, 255, 255);
    private static final Color negativeNeutralColor = new Color(255, 102, 102);
    private static final Color negativeColor = new Color(255, 0, 0);

    private int entryID;
    private LocalDate date;          // date of entry
    private String mood;        // mood of said entry
    private String moodTag;     // tag associated to mood
    private Color color;        // color associated to moodTag
    private String note;        // additional notes for said entry

    // REQUIRES: valid date entry, mood and moodTag has non-zero length
    // EFFECTS: initializes the mood entry with given inputs; moodTag determines color for this entry
    public Mood(int id, LocalDate date, String mood, String moodTag, String note) {
        this.date = date;
        this.mood = mood;
        this.moodTag = moodTag;
        this.note = note;
        this.entryID = id;
        setColor(moodTag);
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
    }

    public void setNote(String note) {
        this.note = note;
    }

    //MODIFIES: this
    //EFFECTS: add current note on top of input note
    public void addNote(String note) {
        this.note = this.note + " " + note;
    }

    //REQUIRES: string to be "Positive", "Positive Neutral", "Neutral", "Negative", "Negative Neutral"
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
        }
    }


}
