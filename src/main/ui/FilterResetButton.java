package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterResetButton extends Button {
    JDialog jdialog;

    public FilterResetButton(MoodTrackerUI moodTrackerUI, JComponent parent, JDialog jdialog) {
        super(moodTrackerUI, parent);
        this.jdialog = jdialog;
    }

    @Override
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
    protected void createButton(JComponent parent) {
        button = new JButton("Reset");
    }
}
