package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button resets displayed moodList
public class FilterResetButton extends Button {
    JDialog jdialog;

    public FilterResetButton(MoodTrackerUI moodTrackerUI, JComponent parent, JDialog jdialog) {
        super(moodTrackerUI, parent);
        this.jdialog = jdialog;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds listener to button, sets up button function
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Reset")) {
                    moodTrackerUI.resetTableToMoodList();
                    jdialog.dispose();
                }
            }
        });
    }

    @Override
    // MODIFIES: this
    // EFFECTS: set button name
    protected void createButton(JComponent parent) {
        button = new JButton("Reset");
    }
}
