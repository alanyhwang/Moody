package ui.dialogui;

import model.Event;
import model.EventLog;
import ui.MoodTrackerUI;
import ui.button.DialogOkButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// creates JDialog to list all the events
public class EventLogUI extends JDialog implements WindowListener {
    private MoodTrackerUI moodTrackerUI;
    private JTextArea eventLogArea;

    public EventLogUI(MoodTrackerUI moodTrackerUI) {
        super(moodTrackerUI, "Event Log", false);
        this.moodTrackerUI = moodTrackerUI;
        addWindowListener(this);
        makeJDialog();
    }

    // MODIFIES: this
    // EFFECTS: initialize new JDialog window
    private void makeJDialog() {
        initializeDialog();
        initializeTextBoxForEventLogs();
        JScrollPane eventLogAreaWithScroll = new JScrollPane(eventLogArea);
        JPanel botButtonPanel = new JPanel();
        new DialogOkButton(moodTrackerUI, botButtonPanel, this);
        add(eventLogAreaWithScroll, BorderLayout.CENTER);
        add(botButtonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create and initialize new text area to display event logs
    private void initializeTextBoxForEventLogs() {
        eventLogArea = new JTextArea();
        for (Event event : EventLog.getInstance()) {
            eventLogArea.append(event.toString());
        }
        eventLogArea.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: reprint event logs to text area
    public void updateEventLogTextBox() {
        eventLogArea.setText("");
        for (Event event : EventLog.getInstance()) {
            eventLogArea.append(event.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: create and initialize new JDialog
    private void initializeDialog() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(350, 300));
        setLocation(525, 0);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        moodTrackerUI.setEventLogOpened(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        moodTrackerUI.setEventLogOpened(false);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
