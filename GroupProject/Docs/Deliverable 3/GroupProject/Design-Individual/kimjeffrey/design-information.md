1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.
-For this requirement, I added three classes: list, reminder, and user. The list class contains
the attribute: reminder. The user is given operations to first selectList(List) and then
to add, delete, and edit reminders from that list.

2. The application must contain a database (DB) of reminders and corresponding data.
-I created a Database class that hold attributes Reminders as well as Reminder types.

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment).
-This will be implemented into the List operation addReminder().

4. Users must also be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the reminder, or add a new one, and then save the new reminder, together with its type, in the DB. 
-The User class is given a searchDB() operation which will search the databases by name. The Database is given a confirmMatch() operation which will check if there is a result that matches the user search. If there is no match, then the user will be able to add the reminder along with its type to the database.

5. The reminders must be saved automatically and immediately after they are modified.
-The Reminder class is given a savetoDB() operation that will be called anytime any data is modified.

6. Users must be able to check off reminders in the list (without deleting them).
The Reminder class is given a checkbox attribute that will be a boolean. It will be true if checked, and false is unchecked.
-The User class is given check(Reminder) and checkAll() operation that will set the checkbox to true for the given Reminder or set all the checkboxes to true.

7. Users must also be able to clear all the check-off marks in the reminder list at once.
-The User class is given a uncheckAll() operation to clear all the check marks in the reminder list at once.

8. Check-off marks for the reminder list are persistent and must also be saved immediately.
-The Reminder class has a checkbox attribute, which will persist, and has a saveToDB() operation that will be called any time data is modified.

9. The application must present the reminders grouped by type.
-The List class is given a sortByType() operation which will sort the reminders by type.

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”,
“Kid’s Reminders”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.
-The User class is given operations addList(), deleteList(), editListName(), and selectList() to create and edit multiple lists.

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.
-The Reminder class is given a setAlert() operation which will be able to set Alerts. An Alert class is made which has the attributes days and time which can be set using the setDays() and setTime() operations.

12. Extra Credit: Option to set up reminder based on location.

13. The User Interface (UI) must be intuitive and responsive.
-This does not affect the design directly.