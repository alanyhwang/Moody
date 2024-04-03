package ui.button;

import ui.MoodTrackerUI;
import ui.dialogui.FilterMoodDialogUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button resets displayed moodList
public class FilterResetButton extends Button {

    FilterMoodDialogUI filterMoodDialogUI;

    public FilterResetButton(MoodTrackerUI moodTrackerUI, JComponent parent, FilterMoodDialogUI filterMoodDialogUI) {
        super(moodTrackerUI, parent);
        this.filterMoodDialogUI = filterMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Reset")) {
                moodTrackerUI.resetTableToMoodList();
                filterMoodDialogUI.disposeDialog();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Reset");
    }
}
