package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;

// class hierarchy inspired by drawing editor
// buttons for mood tracker
public abstract class Button {

    protected JButton button;
    protected MoodTrackerUI moodTrackerUI;

    public Button(MoodTrackerUI moodTrackerUI, JComponent parent) {
        this.moodTrackerUI = moodTrackerUI;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // EFFECTS: adds listener to button, sets up button function
    protected abstract void addListener();

    // EFFECTS: create and set button name
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS: adds button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
