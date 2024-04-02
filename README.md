# Mood Tracker

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

## Instructions for Grader
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
- Entries loaded from moodList.json.
- 
- Tue Apr 02 15:06:45 PDT 2024
- Mood added to entries list!
- ID: 17 on Date: 0001-01-01
- 
- Tue Apr 02 15:06:48 PDT 2024
- Mood deleted from entries list!
- ID: 3 on Date: 1888-02-02
- 
- Tue Apr 02 15:06:50 PDT 2024
- Entries filtered by tag: Neutral