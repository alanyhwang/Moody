package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button prompts filter of moodList
public class FilterButton extends Button {

    public FilterButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Filter")) {
                moodTrackerUI.filterMoods();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Filter");
    }
}
