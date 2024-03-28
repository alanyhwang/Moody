package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton extends Button {
    public LoadButton(MoodTrackerUI moodTrackerUI, JComponent parent) {
        super(moodTrackerUI, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Load")) {
                    moodTrackerUI.loadMoods();
                }
            }
        });
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
    }
}
