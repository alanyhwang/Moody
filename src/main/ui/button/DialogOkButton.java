package ui.button;

import ui.MoodTrackerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogOkButton extends Button {
    private JDialog jdialog;

    public DialogOkButton(MoodTrackerUI moodTrackerUI, JComponent parent, JDialog jdialog) {
        super(moodTrackerUI, parent);
        this.jdialog = jdialog;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("OK")) {
                    jdialog.dispose();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("OK");
    }
}
