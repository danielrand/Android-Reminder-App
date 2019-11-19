# Reminders Application
### Requirements
1. A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in the list.
    #### To realize this requirement, I added to the design classes *Reminder* and *Reminder List*. The class *Reminder List* has an attribute 'name' of type String and operations of addReminder(), deleteReminder(Reminder), and editReminder(Reminder) which allows the *User* to add, delete, or edit Reminders to a list.

2. The application must contain a database (DB) of reminders and corresponding data.
    #### To realize this requirement, I added to the design a class *Database* with attribute reminder of type Reminder. Class *Database* will be able to contain all the variables in the *Reminder* class and store it for future reference. Therefore, it also contains a method, store(Reminder). Also, the Database should have the option to remove the Reminder from the Database.

3. Users must be able to add reminders to a list by picking them from a hierarchical list, where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment).
    #### To realize this requirement, I added to the *Reminder* class attributes 'type' and 'name' which are both of type String along with operations getType(), setType(String), getName(), and setName(String).


4. Users must be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the reminder, or add a new one, and then save the new reminder, altogether with its type, in the DB.
    #### To realize this requirement, I added to the *Reminder* class an operation search(String) which searches for names into the *Database* class. In the *Database* class, it has an operation, match(String), which takes in a parameter of a type String from the reminder variable and determines whether a match or similar match can be found. Otherwise, the application will allow the user to create a new type with the operation createNewType(String) along with the ability to remove the type with the operation removeType(String).


5. The reminders must be saved automatically and immediately after they are modified.
    #### To realize this requirement, I added to the *Reminder* class an operation, autosave(), which automatically saves the reminders after modifications.


6. Users must be able to check off reminders in the list (without deleting them).
    #### To realize this requirement, I added to the *Reminder* class an attribute 'check' of type boolean and the ability to check off the reminders in the list with operations setCheck(Boolean) and to check the status of the checks with getCheck().


7. Users must also be able to clear all the check-off marks in the reminder list at once.
    #### To realize this requirement, I added to the *Reminder List* class the operations, checkAll() and clearChecks(), to clear all the check-off marks in the reminder list as well as the ability to clear all the check-off marks.

8. Check-off marks for the reminder list are persistent and must also be saved immediately.
    #### To realize this requirement, I added to the design a *Persistence* class which will handle the persistence in the *Reminder* class, *Reminder List* class, and the *Database* class. This class has operations save(), delete(), and edit().


9. The application must present the reminders grouped by type.
    #### To realize this requirement, I added to the *UserInterface* class an operation, sort(String) which can group reminders by a given 'type' of type String. 
11. The application must support multiple reminder lists at a time (e.g., "Weekly", "Monthly", "Kid's Reminders"). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists.
    #### To realize this requirement, I added to the *UserInterface* class operations createList(), editList(Reminder List), selectList(Reminder List), and deleteList(Reminder List).


11. The application should have the option to set up reminders with day and time alert. If this option is selected, allow the option to repeat the behavior.
    #### To realize this requirement, I added to the *Reminder* class an attribute 'dayTime' of type boolean which checks whether a day and time alert is selected. I added to the design a class *Alert* with attributes 'type' of type String and 'name' of type String when dayTime is true. The *Alert* class has the operation, notify(), in which another new class was added, *Day and Time*, which has attributes 'day' of type Date, 'time' of type DateTime, and 'repeat' of type boolean which allows the option to repeat the alert given a day and/or time. This class has the operations getDay(), setDay(Date), getTime(), and setTime(DateTime). If repeat is true in the *Day and Time* class, then notify() will repeatedly create an Alert at the designated day and/or time. The *Reminder* class has the operations getDayTimeBool() and setDayTimeBool(Boolean) to manage the dayTime boolean.


12. **Extra Credit:** Option to set up reminder based on location.
    #### To realize this requirement, I added to the *Reminder* class an attribute 'location' of type boolean. I added to the design a *Location* class with an attribute 'location' of type Location and operations getLocation() and setLocation(Location). If the user reaches a particular location, the notify() operation from the *Alert* class will be called. The *Reminder* class has the operations getLocationBool() and setLocationBool(Boolean) to manage the location boolean.

13. The User Interface (UI) must be intuitive and responsive.
    #### This part of the requirement does not necessarily affect the design.


