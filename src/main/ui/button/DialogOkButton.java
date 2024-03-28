package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Closes dialog
public class DialogOkButton extends Button {
    private JDialog jdialog;

    public DialogOkButton(MoodTrackerUI moodTrackerUI, JComponent parent, JDialog jdialog) {
        super(moodTrackerUI, parent);
        this.jdialog = jdialog;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener((ActionEvent e) -> {
            String s = e.getActionCommand();
            if (s.equals("OK")) {
                jdialog.dispose();
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton(JComponent parent) {
        button = new JButton("OK");
    }
}
