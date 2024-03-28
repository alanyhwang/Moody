package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button saves current moods in moodList to JSON
public class SaveButton extends Button {
    public SaveButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Save")) {
                moodTrackerUI.saveMoods();
            }
        });
    }


    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
    }
}
