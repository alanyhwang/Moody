package ui.button;

import ui.dialogui.FilterMoodDialogUI;
import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Button confirms filtering
public class FilterConfirmButton extends Button {
    private FilterMoodDialogUI filterMoodDialogUI;

    public FilterConfirmButton(MoodTrackerUI mtUI, JComponent parent, FilterMoodDialogUI filterMoodDialogUI) {
        super(mtUI, parent);
        this.filterMoodDialogUI = filterMoodDialogUI;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("Confirm")) {
                String moodTag = filterMoodDialogUI.getSelectedMoodTag();
                moodTrackerUI.updateFilteredMoodTable(moodTag);
                filterMoodDialogUI.disposeDialog();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton() {
        button = new JButton("Confirm");
    }
}
