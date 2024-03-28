package ui.dialogui;

import model.Mood;
import ui.MoodTrackerUI;
import ui.button.AddCancelButton;
import ui.button.AddDoneButton;

import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

// Extract constructor information to create new mood from DialogUI
public class AddMoodDialogUI {

    protected static final String[] moodTagChoices =
            { "Positive","Positive Neutral", "Neutral","Negative Neutral","Negative"};

    protected JDialog dialog;
    protected MoodTrackerUI moodTrackerUI;
    protected int moodID;
    protected LocalDate date;
    protected String mood;
    protected String moodTag;
    protected String note;
    protected JTextArea noteTextArea;
    protected JComboBox<String> moodTagComboBox;
    protected JTextField moodTextField;
    protected JTextField yearTextField;
    protected JTextField monthTextField;
    protected JTextField dayTextField;

    public AddMoodDialogUI(MoodTrackerUI moodTrackerUI) {
        this.moodTrackerUI = moodTrackerUI;
        resetMoodParameters();
        makeJDialog();
    }

    // MODIFIES: this
    // EFFECTS: reset mood parameters
    private void resetMoodParameters() {
        moodID = 0;
        date = null;
        mood = null;
        moodTag = null;
        note = null;
    }

    // MODIFIES: this
    // EFFECTS: creates new JDialog to add mood
    protected void makeJDialog() {
        initializeDialog();
        JPanel centerPanel = initializeCenterPanel();
        JPanel centerTopPanel = initializeCenterPanel();
        JPanel centerBotPanel = initializeCenterPanel();
        centerPanel.add(centerTopPanel);
        centerPanel.add(centerBotPanel);
        addDatePanel(centerTopPanel);
        addMoodPanel(centerTopPanel);
        addMoodTagPanel(centerTopPanel);
        addNotePanel(centerBotPanel);
        addDialogButtons();
        dialog.add(centerPanel);
        dialog.setVisible(true);
    }

    // EFFECTS: initialize panel
    protected JPanel initializeCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
        return centerPanel;
    }

    // MODIFIES: this
    // EFFECTS: add buttons to panel, add panel to dialog
    protected void addDialogButtons() {
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        new AddDoneButton(moodTrackerUI, p5, this);
        new AddCancelButton(moodTrackerUI, p5, this);
        if (dialog != null) {
            dialog.add(p5, BorderLayout.SOUTH);
        }
    }

    // MODIFIES: this, p
    // EFFECTS: add note text area to panel
    protected void addNotePanel(JPanel p) {
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(0, 1));
        JLabel noteLabel = new JLabel("Note: ");
        noteLabel.setVisible(true);
        p4.add(noteLabel);
        noteTextArea = new JTextArea(50, 10);
        noteTextArea.setLineWrap(true);
        noteTextArea.setVisible(true);
        p4.add(noteTextArea);
        p.add(p4);
    }

    // MODIFIES: this, p
    // EFFECTS: add moodTag text box to panel
    protected void addMoodTagPanel(JPanel p) {
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel moodTagLabel = new JLabel("MoodTag: ");
        moodTagLabel.setVisible(true);
        p3.add(moodTagLabel);
        moodTagComboBox = new JComboBox<>(moodTagChoices);
        p3.add(moodTagComboBox);
        p3.setVisible(true);
        p.add(p3);
    }

    // MODIFIES: this, p
    // EFFECTS: add mood text box to panel
    protected void addMoodPanel(JPanel p) {
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel moodLabel = new JLabel("Mood: ");
        moodLabel.setVisible(true);
        p2.add(moodLabel);
        moodTextField = new JTextField(10);
        p2.add(moodTextField);
        p2.setVisible(true);
        p.add(p2);
    }

    // MODIFIES: this, p
    // EFFECTS: add date text box to panel
    protected void addDatePanel(JPanel p) {
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("Date(YYYY/MM/DD): ", JLabel.LEFT);
        dateLabel.setVisible(true);
        p1.add(dateLabel);
        yearTextField = new JTextField(3);
        monthTextField = new JTextField(2);
        dayTextField = new JTextField(2);
        p1.add(yearTextField);
        p1.add(monthTextField);
        p1.add(dayTextField);
        p1.setVisible(true);
        p.add(p1);
    }

    // MODIFIES: this
    // EFFECTS: set dialog parameters
    protected void initializeDialog() {
        this.dialog = new JDialog(moodTrackerUI, "New Mood Entry", true);
        dialog.setLayout(new BorderLayout());
        dialog.setMinimumSize(new Dimension(250, 300));
        dialog.setLocation(500, 0);
    }

    // EFFECTS: reset parameters and close dialog
    public void cancelDialog() {
        resetMoodParameters();
        disposeDialog();
    }

    // EFFECTS: create new mood with new mood constructors
    public Mood constructNewMood() {
        Mood m = null;
        convertJComponentsToMoodConstructors();
        if (moodID != 0 && date != null && moodTag != null) {
            m = new Mood(moodID, date, mood, moodTag, note);
        }
        return m;
    }

    // MODIFIES: this
    // EFFECTS: convert JComponent fields to this
    private void convertJComponentsToMoodConstructors() {
        try {
            String yearText = getYearText();
            String monthText = getMonthText();
            String dayText = getDayText();
            String dateString = yearText + "-" + monthText + "-" + dayText;
            date = LocalDate.parse(dateString);
            note = noteTextArea.getText();
            moodTag = Objects.requireNonNull(moodTagComboBox.getSelectedItem()).toString();
            mood = moodTextField.getText();
            moodID = moodTrackerUI.getMoodList().getMoodID();
        } catch (DateTimeException e) {
            System.out.print("Date Exception");
            String name = "Invalid Date Error";
            String message = "Date is invalid. Choose another date.";
            new MessageDialogUI(moodTrackerUI, name, message, false);
        }
    }

    // EFFECTS: close dialog
    public void disposeDialog() {
        dialog.dispose();
    }

    // EFFECTS: convert text field year to string
    protected String getYearText() {
        String yearFromText = yearTextField.getText();
        if (yearFromText.length() == 1) {
            String yearText = "000" + yearFromText;
            return yearText;
        }
        if (yearFromText.length() == 2) {
            String yearText = "00" + yearFromText;
            return yearText;
        }
        if (yearFromText.length() == 3) {
            String yearText = "0" + yearFromText;
            return yearText;
        }
        return yearFromText;
    }

    // EFFECTS: convert text field day to string
    protected String getDayText() {
        if (dayTextField.getText().length() == 1) {
            String dayText = "0" + dayTextField.getText();
            return dayText;
        }
        return dayTextField.getText();
    }

    // EFFECTS: convert text field month to string
    protected String getMonthText() {
        if (monthTextField.getText().length() == 1) {
            String monthText = "0" + monthTextField.getText();
            return monthText;
        }
        return monthTextField.getText();
    }
}
