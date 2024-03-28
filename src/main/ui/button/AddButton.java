package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button adds new mood entry to moodList
public class AddButton extends Button {
    JComponent parent;

    public AddButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
        this.parent = parent;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Add")) {
                moodTrackerUI.addNewMood();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton(JComponent parent) {
        button = new JButton("Add");
    }
}
