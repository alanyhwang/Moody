package ui.dialogui;

import ui.MoodTrackerUI;
import ui.button.FilterConfirmButton;
import ui.button.FilterResetButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// creates JDialog to obtain desired moodTag to filter moodList
public class FilterMoodDialogUI {
    private MoodTrackerUI moodTrackerUI;
    private JComboBox<String> moodTagComboBox;
    private String moodTag;
    private JDialog dialog;

    public FilterMoodDialogUI(MoodTrackerUI moodTrackerUI) {
        this.moodTrackerUI = moodTrackerUI;
        moodTagComboBox = null;
        moodTag = null;
    }

    // MODIFIES: this
    // EFFECTS: get selected moodTag from JComboBox UI
    public String getSelectedMoodTag() {
        moodTag = Objects.requireNonNull(moodTagComboBox.getSelectedItem()).toString();
        return moodTag;
    }

    // MODIFIES: this
    // EFFECTS: creates JDialog and components
    public void createDialogFilter() {
        dialog = new JDialog(moodTrackerUI, "Filter Mood Entries", true);
        dialog.setLayout(new GridLayout(0, 1));
        dialog.setMinimumSize(new Dimension(200, 150));
        dialog.setLocation(250, 100);
        setupTopDialog();
        setupFilterButtons();
        dialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to bottom panel, and panel to JDialog
    private void setupFilterButtons() {
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        new FilterConfirmButton(moodTrackerUI, p2,this);
        new FilterResetButton(moodTrackerUI, p2, this);
        p2.setVisible(true);
        dialog.add(p2);
    }

    // MODIFIES: this
    // EFFECTS: adds text and JComboBox to top panel, and panel to JDialog
    private void setupTopDialog() {
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
        dialog.add(p1);
    }

    // MODIFIES: this
    // EFFECTS: exit JDialog
    public void disposeDialog() {
        dialog.dispose();
    }
}
