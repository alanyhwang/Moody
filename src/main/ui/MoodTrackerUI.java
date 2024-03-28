package ui;

import model.Mood;
import model.MoodList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.button.*;
import ui.dialogui.AddMoodDialogUI;
import ui.dialogui.EditMoodDialogUI;
import ui.dialogui.FilterMoodDialogUI;
import ui.dialogui.MessageDialogUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

// application's main window frame
public class MoodTrackerUI extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private static final String JSON_LOCATION = "./data/moodlist.json";
    private static final String[] columnNames = {"ID", "Date", "Mood"};

    private MoodList moodList;
    private DefaultTableModel editableTableMoodList;
    private JTable editableJTableMoodList;
    private JTextArea textArea;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MoodTrackerUI() {
        super("Mood Tracker");
        initializeTracker();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initialize fields
    private void initializeTracker() {
        moodList = new MoodList();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
    }

    public MoodList getMoodList() {
        return moodList;
    }

    // MODIFIES: this
    // EFFECTS: create JFrame window JList for moods, JButton to operate on moods, and note panel for each mood
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        addButtons();
        addMoodListAndTextArea();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: add JPanel with JTable for MoodList and JTextArea for notes
    private void addMoodListAndTextArea() {
        JPanel rightArea = new JPanel();
        rightArea.setLayout(new GridLayout(0,1));
        rightArea.setMaximumSize(new Dimension(100, 300));
        //JTextArea
        textArea = setTextArea();
        JScrollPane textAreaScroll = setScrollPaneForTextArea(textArea);
        //JTable
        JTable table = setMoodListTable();
        selectionListener(table, textArea);
        JScrollPane tableScroll = setScrollPaneForTable(table);

        rightArea.add(tableScroll);
        rightArea.add(textAreaScroll);
        add(rightArea,BorderLayout.CENTER);
    }

    // MODIFIES: this, textArea
    // EFFECTS: mouse click on a JTable row will set JTextArea's text to the associated mood
    private void selectionListener(JTable table, JTextArea textArea) {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() > -1) {
                    textArea.setText("");
                    int rowMoodID = parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    Mood m = moodList.findMood(rowMoodID);
                    String id = "ID: " + m.getID() + "\n";
                    String date = "Date: " + m.getDate() + "\n";
                    String mood = "Mood: " + m.getMood() + "\n";
                    String moodTag = "MoodTag: " + m.getMoodTag() + "\n";
                    String note = "Note: " + "\n" + m.getNote();
                    textArea.setText(id + date + mood + moodTag + note);
                    textArea.setCaretPosition(0);
                }
            }
        });
    }

    // EFFECTS: make JScrollPane for textArea
    private JScrollPane setScrollPaneForTextArea(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(100, 125));
        return scrollPane;
    }

    // EFFECTS: initialize textArea for note display
    private JTextArea setTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        return textArea;
    }

    // EFFECTS: make JScrollPane for table
    private JScrollPane setScrollPaneForTable(JTable table) {
        JScrollPane js = new JScrollPane(table);
        js.setPreferredSize(new Dimension(100, 175));
        js.setVisible(true);
        return js;
    }

    private JTable setMoodListTable() {
        ArrayList<Mood> moods = moodList.getMoodList();
        DefaultTableModel moodTable = new DefaultTableModel(columnNames, 0);
        for (Mood m : moods) {
            String[] eachMood = {String.valueOf(m.getID()), String.valueOf(m.getDate()), m.getMood()};
            moodTable.addRow(eachMood);
        }
        editableTableMoodList = moodTable;
        JTable table = new JTable(moodTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        editableJTableMoodList = table;
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }


    // MODIFIES: this
    // EFFECTS: add buttons at left side of JFrame
    private void addButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setMaximumSize(new Dimension(100, 300));
        add(buttonArea, BorderLayout.WEST);

        new AddButton(this, buttonArea);
        new RemoveButton(this, buttonArea);
        new FilterButton(this, buttonArea);
        new EditButton(this, buttonArea);
        new SaveButton(this, buttonArea);
        new LoadButton(this, buttonArea);
    }

    public void addNewMood() {
        new AddMoodDialogUI(this);
    }

    public void removeMood() {
        if (editableJTableMoodList.getSelectedRow() > -1) {
            int rowToDelete = editableJTableMoodList.getSelectedRow();
            int removeMoodID = parseInt(editableJTableMoodList.getValueAt(rowToDelete, 0).toString());
            editableTableMoodList.removeRow(rowToDelete);
            moodList.deleteMood(removeMoodID);
        }
    }

    public void filterMoods() {
        FilterMoodDialogUI filterMoodDialogUI = new FilterMoodDialogUI(this);
        filterMoodDialogUI.createDialogFilter();
    }

    public void editMood() {
        int rowToEdit = editableJTableMoodList.getSelectedRow();
        if (rowToEdit > -1) {
            int moodIDToEdit = parseInt(editableJTableMoodList.getValueAt(rowToEdit, 0).toString());
            Mood m = moodList.findMood(moodIDToEdit);
            new EditMoodDialogUI(this, m);
        }
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: loads all mood entries from MoodList from file
    public void loadMoods() {
        try {
            moodList = jsonReader.read();
            System.out.println("Loaded previous save from " + JSON_LOCATION);
            String name = "Load Successful";
            String message = "Mood Entries Loaded!";
            new MessageDialogUI(this, name, message, true);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
            String name = "Load Failed";
            String message = "Load Failed!";
            new MessageDialogUI(this, name, message, false);
        }
        resetTableToMoodList();
    }

    // TODO
    // EFFECTS: saves all mood entries to file
    public void saveMoods() {
        try {
            jsonWriter.open();
            jsonWriter.write(moodList);
            jsonWriter.close();
            System.out.println("Saved your mood entries to " + JSON_LOCATION);
            String name = "Save Successful";
            String message = "Mood Entries Saved!";
            new MessageDialogUI(this, name, message, true);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
            String name = "Save Failed";
            String message = "Save Failed";
            new MessageDialogUI(this, name, message, false);
        }
    }

    public void addNewMoodToMoodList(Mood m) {
        moodList.addMood(m);
        updateJTableUIAddMood(m);
    }

    private void updateJTableUIAddMood(Mood m) {
        String[] newMood = {String.valueOf(m.getID()), String.valueOf(m.getDate()), m.getMood()};
        editableTableMoodList.addRow(newMood);
    }

    public void updateFilteredMoodTable(String moodTag) {
        ArrayList<Mood> filteredMoodList = moodList.filterMoodByTag(moodTag);
        editableTableMoodList.setRowCount(0);
        for (Mood m : filteredMoodList) {
            String[] eachMood = {String.valueOf(m.getID()), String.valueOf(m.getDate()), m.getMood()};
            editableTableMoodList.addRow(eachMood);
        }
    }

    public void resetTableToMoodList() {
        editableTableMoodList.setRowCount(0);
        for (Mood m : moodList.getMoodList()) {
            String[] eachMood = {String.valueOf(m.getID()), String.valueOf(m.getDate()), m.getMood()};
            editableTableMoodList.addRow(eachMood);
        }
        textArea.setText("");
    }
}
