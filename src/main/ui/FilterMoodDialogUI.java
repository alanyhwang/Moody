package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FilterMoodDialogUI {
    private MoodTrackerUI moodTrackerUI;
    private JComboBox<String> moodTagComboBox;
    private String moodTag;
    private Dialog dialog;

    // Obtain desired MoodTag to filter from DialogUI
    public FilterMoodDialogUI(MoodTrackerUI moodTrackerUI) {
        this.moodTrackerUI = moodTrackerUI;
        moodTagComboBox = null;
        moodTag = null;
    }

    public String getSelectedMoodTag() {
        moodTag = Objects.requireNonNull(moodTagComboBox.getSelectedItem()).toString();
        return moodTag;
    }

    public void createDialogFilter() {
        JDialog d = new JDialog(moodTrackerUI, "Filter Mood Entries", true);
        dialog = d;
        d.setLayout(new GridLayout(0, 1));
        d.setMinimumSize(new Dimension(200, 150));
        d.setLocation(250, 100);
        setupTopDialog(d);
        setupFilterButtons(d);
        d.setVisible(true);
    }

    private void setupFilterButtons(JDialog d) {
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        new FilterConfirmButton(moodTrackerUI, p2,this);
        new FilterResetButton(moodTrackerUI, p2, d);
        p2.setVisible(true);
        d.add(p2);
    }

    private void setupTopDialog(JDialog d) {
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel moodTagLabel = new JLabel("MoodTag: ");
        moodTagLabel.setVisible(true);
        p1.add(moodTagLabel);
        String[] moodTagChoices = { "Positive","Positive Neutral", "Neutral","Negative Neutral","Negative"};
        JComboBox<String> moodTagCB = new JComboBox<>(moodTagChoices);
        moodTagComboBox = moodTagCB;
        p1.add(moodTagCB);
        p1.setVisible(true);
        d.add(p1);
    }

    public void disposeDialog() {
        dialog.dispose();
    }
}
