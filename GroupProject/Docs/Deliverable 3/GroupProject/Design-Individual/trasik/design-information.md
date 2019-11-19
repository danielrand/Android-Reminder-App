# Design Information for Reminder Application Class UML
## Torendra Rasik 370: Software Engineering

1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

Users have the ability to create a instance of a ReminderList which allows them to add, delete and edit Reminders to the list.

2. The application must contain a database (DB) of reminders and corresponding data.

To realize this requirement, I added to the design a class Database with attributes reminderName, reminderType, and reminderData. The Database
must model an actual database that stores the ReminderType along with the Reminders, where Users are able to retrieve Reminders and add it to their list.

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the
name of the actual reminder (e.g., Dentist Appointment).

In order to realize this requirement, I created a parent class ReminderType that represents the first level to figure out which type the reminder is.
Next, I constructed a child class of ReminderType called Reminders(Multi-purpose) which inherits ReminderType properties and gives it an actual name.

4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB.

In the case of Users using the Database, I constructed the class diagram in a way where Users are able to search for a Reminder name. If it
isn't available within the database then the user has an option of adding a new Remainder Type or use a pre exisiting type within the database.

5. The reminders must be saved automatically and immediately after they are modified.

Each reminder, even if not in use, must be saved after they are added or edited. This means that the entire application is dependant on the database.

6. Users must be able to check off reminders in the list (without deleting them).

To represent this, I allowed for a boolean check value within each reminder, that way if they are checked off that means they are inactive but not deleted.

7. Users must also be able to clear all the check-off marks in the reminder list at once.

As the above requirement, I allowed for the ReminderList to be able to checkOff all Reminders by using the boolean checkedOff value within each requirement.

8. Check-off marks for the reminder list are persistent and must also be saved immediately.

To represent this, I allowed for the relationship between the Reminder class and the database to be dependant and a two-way association that way we know that data
must be saved immediately after a change in the checkedOff boolean.

9. The application must present the reminders grouped by type.

Since ReminderTypes are inherited by the Reminder class, I allowed for the ReminderList class(Which consists of Reminders) to be able to group Reminders based on their
types by using a operation

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”,
“Kid’s Reminders”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.

In order to represent this relationship, I allowed for multiplicities between the User and ReminderList class such that 1 User can have 0 or more ReminderLists
within the application.

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.

In order to represent this within the diagram, I allowed for Utility classes since time and date are abstract values, there is no need to create a class for these
as they already exist.

12. Extra Credit: Option to set up reminder based on location.

My idea on using a Utility class to represent location is that, the location for a reminder can be set through an API so it seems it may be abstract enough for me to
not create a class for it.

13. The User Interface (UI) must be intuitive and responsive

Not considered because it does not affect the design directly