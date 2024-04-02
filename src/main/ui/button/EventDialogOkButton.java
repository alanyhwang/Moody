package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EventDialogOkButton extends DialogOkButton {
    public EventDialogOkButton(MoodTrackerUI moodTrackerUI, JComponent parent, JDialog jdialog) {
        super(moodTrackerUI, parent, jdialog);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("OK")) {
                moodTrackerUI.setEventLogOpened(false);
                jdialog.dispose();
            }
        });
    }
}
