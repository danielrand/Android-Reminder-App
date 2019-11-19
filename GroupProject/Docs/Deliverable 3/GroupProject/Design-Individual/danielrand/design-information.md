# Daniel Rand - Reminder App

## Design Information

1. A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in the list.

	1. I achieved this requirement by creating Reminder, ReminderList, and User classes.
	2. The User class has methods to add create, delete and edit reminders from a list by accessing the database of ReminderLists.
	
2. The application must contain a database (DB) of reminders and corresponding data.
	1. I achieved this requirement by creating a Database class containing reminderLists with their associated user information.
	
3. Users must be able to add reminders to a list by picking them from a hierarchical list, where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment).
	1. I achieved this by creating a Reminder List class. The reminder list class is a list of reminders grouped by category. The database is a list of these reminder lists.
	2. The User class has methods to retreive reminder lists and alphabatize the results to display them.
	
4. Users must also be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the reminder, or add a new one, and then save the new reminder, together with its type, in the DB.
	1. I achieved this by adding a query database function in the user class that, as the name implies, searches the database for a given reminder name and if found there is a method called createReminderFromDB that can create the reminder from the template of the retreived reminder in the database.
	2. Otherwise the user will be prompted to create a reminder via the standard create reminder function.
	
5. The reminders must be saved automatically and immediately after they are modified.
	1. This will happen by definition as reminders are objects and mutating an instance of the object will permanently change the state of the reminder.
	
6. Users must be able to check off reminders in the list (without deleting them).
	1. I achieved this with a isChecked boolean attribute in the reminders class.
	
7. Users must also be able to clear all the check-off marks in the reminder list at once.
	1. I achieved this with a clearChecks() function in the user class which will iterate through all reminder lists and uncheck them.
	
8. Check-off marks for the reminder list are persistent and must also be saved immediately.
	1. This will happen by definition as isChecked is an attribute of the Reminder class and instances of the class can have their attribute immediately modified with setChecked()
	
9. The application must present the reminders grouped by type.
	1. I acheived this by designed the database as a list of ReminderLists, and ReminderLists themselves are grouped by category. In order to display them in alphabetical order I included sort functions in the User class.
	
10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”, “Kid’s Reminders”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists.
	1. See # 9
	2. The user class contains methods to delete, create, rename, and select reminder lists from the databse.
	
11. The application should have the option to set up reminders with day and time alert. If this option is selected allow option to repeat the behavior.
	1. I outlined an Alert class for this. Alerts are an optional attribute of the reminder class. Alerts have date and time attributes as well as a repeat option.
	
12. Extra Credit: Option to set up reminder based on location.
13. The User Interface (UI) must be intuitive and responsive.
	As outlined in the guidelines this UML diagram does not specify GUI requirements.