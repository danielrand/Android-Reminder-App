# Test Plan

**Author**: Jeffrey Kim

## 1 Testing Strategy

### 1.1 Overall strategy

For unit testing, we will first see if we can create all our different classes: Reminders, ReminderLists, and Alerts. We will test if all their constructors and methods work.

For integration testing, we will test the different Android Activities to see if the buttons and inputs open the correct Activities and create the correct Reminders and Lists.

For system testing, we will try recreating a typical user experience: from creating lists and reminders, to testing if the user gets the alert created for a Reminder. 

For regression testing, we will have to retest all test cases that are related to the change. 

### 1.2 Test Selection

We will select our test cases based on condition testing. We will first test the creation of a List and Reminder with pass and fail conditions. Then we will test the different methods in the classes of our application with pass and fail conditions. Then we will test the creation of Lists, Reminder, and alerts as a user, assuming everything works.

### 1.3 Adequacy Criterion

When Unit testing, we can assess the quality of our test cases by calculating the number of line of code we are executing with the test case divided by the total lines of code in the unit (class) we are testing times 100. When we are Integration and System testing, we can assess the quality of our test cases by calculating the number of lines of code we are executing with the test case divided by the total lines of code in the entire application times 100.

### 1.4 Bug Tracking

We can have a form that a user can fill out to submit bugs and feature requests which will send out an email to the team.

### 1.5 Technology

We will use JUnit and Espresso tests in Android Studio to implement tests.

## 2 Test Cases

| Test Case                                                    				| Purpose                           																| Steps Necessary 																									| Expected Result 															| Actual Result 													| Pass/Fail | Add'l Info |
|--------------------------------------------------------------				|-----------------------------------																|-----------------																									|-----------------															|---------------													|-----------|----------- |
| Press the '+' button in the MainActivity.									| See behavior of pressing the '+' button in the MainActivity.										| Press the '+' Button																								| The CreateReminderActivity is launched									| The CreateReminderActivity is launched							| Pass		|
| Press the 'Cancel' button in the CreateReminderActivity					| See behavior of pressing the 'Cancel' button in the CreateReminderActivity						| Press the '+' Button then press the 'Cancel' Button																| We go back to the Main Activity page										| Return to the MainActivity 										| Pass		|
| Create a Reminder with only 'Type' Attribute.								| See behavior of trying to create a Reminder with only 'Type' Attribute.							| Press the '+' Button then press the 'Done' button with 'Type' attribute filled.									| A toast notifying of missing attributes.									| A toast notifiying that Description and Type need to be inputted. | Pass		|
| Create a Reminder with only 'Description' Attribute.						| See behavior of trying to create a Reminder with only 'Description' Attribute.					| Press the '+' Button then press the 'Done' button with 'Description' attribute filled.							| A new Reminder with given attributes.										| A toast notifiying that Description and Type need to be inputted. | Pass		|
| Create a Reminder with both 'Type' and 'Description' Attribute.			| See behavior of trying to create a Reminder with both 'Type' and 'Description' Attribute.			| Press the '+' Button then press the 'Done' button with 'Type' and 'Description attributes filled.					| A toast notifying of missing attributes.									| A new Reminder with Description in appropriate List.				| Pass		|
| Create a Reminder with an Alert with only 'Time' Attribute.				| See behavior of trying to create a Reminder with an Alert with only 'Time' Attribute.				| Press the '+' Button then press the 'Done' button with 'Time' attribute filled.									| A toast notifying of missing attributes.									| A toast notifiying that Time and Date need to be inputted. 		| Pass		|
| Create a Reminder with an Alert with only 'Date' Attribute.				| See behavior of trying to create a Reminder with an Alert with only 'Date' Attribute.				| Press the '+' Button then press the 'Done' button with 'Date' attribute filled.									| A toast notifying of missing attributes.									| A toast notifiying that Time and Date need to be inputted. 		| Pass		|
| Create a Reminder with an Alert with both 'Time' and 'Date' Attributes.	| See behavior of trying to create a Reminder with an Alert with both 'Time' and 'Date' Attributes.	| Press the '+' Button then press the 'Done' button with 'Time' and 'Date' attribute filled.						| A toast notifying of missing attributes.									| A new Reminder with an Alert with given Time and Date.| Pass		| Pass		|
| Add Reminder to List with no existing List.        		          		| See behavior of trying to add a Reminder to a List when no List exists. 							| Press the '+' Button then press the 'Done' button with a nonexisting 'Type' and 'Description attributes filled.	| A new List with the Reminder added to it.									| A new Reminder List created with the Reminder in that List.		| Pass		|
| Add Reminder to an existing List        						        	| See behavior of trying to add a Reminder to an existing List.					 					| Try to Add a Reminder to an existing List.																		| A new Reminder in given List.												| A new Reminder in the existing Reminder List.						| Pass		|
| Delete Reminder from List       					     	 				| See behavior of trying to delete a Reminder from a List.					 						| Press the 'X' button next to the Reminder to delete.																| Removal of Reminder in given List.										| The chosen Reminder is deleted from the List.						| Pass		|
| Edit Reminder from List             						 				| See behavior of trying to edit a Reminder in a List.												| Press the Pencil button next to the Reminder to edit.																| The EditReminderActivity is launched.										| The EditReminderActivity is launched.								| Pass		|			
| Edit a Reminder Alert from an existing Reminder with an existing alert.	| See behavior of trying to edit a Reminder Alert from an existing Reminder with an existing alert.	| Press the Pencil button next to the Reminder to edit, then click 'Done' button with 'Time' and/or 'Date' filled.	| The Alert description changed for the chosen Reminder.					| A new Alert for a given existing Reminder.						| Pass		|
| Check off a Reminder from a List      									| See behavior of trying to check off a Reminder from a List. 										| Press the checkbox on the left side of the Reminder to check.														| Reminder in given List isChecked = true									| The chosen Reminder's check attribute is true.					| Pass		|
| Check off all Reminders from a List with no existing Reminder. 			| See behavior of trying to check off all Reminders in a List when no Reminders exists. 			| Press the checkbox on the left side of the List to check all.														| The List isChecked = true	and nothing else happens.						| The chosen List's check attribute is true.						| Pass		|
| Check off all Reminders from a List  						 				| See behavior of trying to check off all Reminders in a List.					 					| Press the checkbox on the left side of the List to check all.														| The List isChecked = true and all Reminders in the List isChecked = true	| All the Reminders' check attributes in chosen List are true.		| Pass		|
| Test if alarm rings at the selected time and day.							| See if alarm rings at the given time and day.														| Set an alert to ring at a specific time and day and see if it rings.												| A notification of the respective Reminder.								| A notification of the List and Reminder at the given Time and Date| Pass		|																|			|