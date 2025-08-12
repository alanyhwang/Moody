# Moodiary

[![image](https://github.com/user-attachments/assets/92e6ee97-2a33-405f-8de7-895634e08336)](https://youtu.be/7JoWdo9rpSw "Moodiary demo - Click to Watch!")

Click image to watch demo!

## Description

For people who are interested in tracking their own mood and
finding patterns on how certain events affect their mood.

This application allows users to keep track of their 
mood with each entry, ideally once a day. User will be
able to input a **mood** (for example happy or sad) and
be able to add in additional notes on why they may feel
that way. Each mood will have **tags** that indicate if
the mood is either _positive_, _positive-neutral_,
_neutral_, _negative-neutral_, or _negative_. Each tag
will be color coded from green (positive) to red
(negative). The user can add and define their own mood
with its associated tag. Anyone can use the mood tracker
to reflect on their day and find patterns on how certain
events can affect their mood in the long run.

## User Stories

- As a user, I want to be able to add an entry to my mood tracker list
- As a user, I want to be able to view the date entries (with colored tags) to my mood tracker list
- As a user, I want to be able to select an entry from my mood tracker list and view the notes in detail
- As a user, I want to be able to edit an entry from my mood tracker list, for example, editing the date, mood tag, or notes)
- As a user, I want to be able to delete an entry from my mood tracker list
- As a user, I want to be able to filter entries by their moodTags from my mood tracker list
- As a user, I want to be able to save my mood entries from the main menu to file
- As a user, I want to be able to load my mood entries from the main menu from file

## Instructions
- Load pre-saved mood entries by clicking the Load button. Each entry is color coded from greener = more positive to redder = more negative.
- Click on a row in the table to select and see entry details on the mood entry
- Click the Add button to add a new mood entry, a new dialog window will open. If user enters an invalid date, another dialog will open and notify user fail to add date. An image of sad cat will appear along with the message. Successful entries will add the new entry at the end of the table.
- To remove an entry, select a mood from the table and press the Remove button
- To filter the entry list by their moodTag, press the Filter button, select the desired moodTag and press confirm. To reset, press the filter button and press reset. Entries can be removed with the filtered list.
- To edit an entry, select the desired row from the table, press the Edit button and edit the entry. Confirm to change the entry. Invalid dates will open an error dialog same as invalid date for adding an entry.
- To save an entry, press the Save button, a message will appear if save is successful with a happy cat image. If failed, sad cat image will appear.
- To load an entry, press the Load button, a message will appear if load is successful with a happy cat image. If failed, sad cat image will appear.

## Phase 4: Task 2
Example EventLog:
- Wed Apr 03 10:15:09 PDT 2024
- Entries loaded from moodList.json.
-
- Wed Apr 03 10:15:36 PDT 2024
- Mood added to entries list!
- ID: 18 on Date: 1992-01-14
- 
- Wed Apr 03 10:15:56 PDT 2024 
- Entries filtered by tag: Neutral
- 
- Wed Apr 03 10:16:47 PDT 2024 
- Mood deleted from entries list!
- ID: 16 on Date: 0055-05-05 
- 
- Wed Apr 03 10:16:49 PDT 2024 
- Entries saved to moodList.json.


## Phase 4: Task 3
Ideas for refactoring:
- all the classes in dialogui package can extend JDialog, making them JDialogs, so we don't have to create new JDialogs within those classes. Also, more consistent with naming.
- buttons appears in same windows can be grouped to the same package? Helps when searching for the right button class
- buttons can probably extend JButtons, and main Button abstract class can be more general, taking only JComponent as parameter, and have other button classes that extend it add necessary parameters of their own. Currently, Button class take in MoodTrackerUI which not all child buttons need.
- make moodTag values enums in mood class ("Positive", "Positive Neutral", "Neutral", "Negative Neutral", "Negative")
- have a editMood method in MoodList class that passes in moodID of edited mood and new parameters; this way we can log when moods are being edited in the event log and we're not editing moods in the UI class
