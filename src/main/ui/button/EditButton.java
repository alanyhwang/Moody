package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button prompt edits to selected mood
public class EditButton extends Button {

    public EditButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Edit")) {
                moodTrackerUI.editMood();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Edit");
    }
}
