package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button removes selected mood entry
public class RemoveButton extends Button {

    public RemoveButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Remove")) {
                moodTrackerUI.removeMood();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Remove");
    }
}
