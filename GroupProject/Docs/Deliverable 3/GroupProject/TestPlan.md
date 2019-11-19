# Test Plan

**Author**: Jeffrey Kim

## 1 Testing Strategy

### 1.1 Overall strategy

For unit testing, we will first see if we can create all our different classes: Reminders, ReminderLists, ReminderTypes, and Alerts. We will test if all their constructors and methods work.

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

| Test Case                                                    		| Purpose                           															| Steps Necessary 														| Expected Result 															| Actual Result 													| Pass/Fail | Add'l Info |
|--------------------------------------------------------------		|-----------------------------------															|-----------------														|-----------------															|---------------													|-----------|----------- |
| Press the '+' button in the Main Activity.						| See behavior of pressing the '+' button in the Main Activity.									| Press the '+' Button													| Buttons to 'Create Reminder', 'Create List', and 'Cancel' appear			| Buttons to 'Create Reminder', 'Create List', and 'Cancel' appear	| Pass		|
| Press the 'Create Reminder' button in the Main Activity			| See behavior of pressing the 'Create Reminder' button in the Main Activity					| Press the '+' Button, then the 'Create Reminder' button				| A new Activity 'CreateReminderActivity' is launched.						| The 'CreateReminderActivity' is launched							| Pass		|
| Press the 'Create List' button in the Main Activity				| See behavior of pressing the 'Create List' button in the Main Activity						| Press the '+' Button, then the 'Create List' button					| A new Activity 'CreateReminderListActivity' is launched.					| The 'CreateReminderListActivity' is launched						| Pass		|
| Press the 'Cancel' button in the Main Activity					| See behavior of pressing the 'Cancel' button in the Main Activity								| Press the '+' Button, then the 'Cancel' button						| We go back to the Main Activity page										| Return to the Main Activity page 									| Pass		|
| Press the 'Cancel' button in the CreateReminder Activity			| See behavior of pressing the 'Cancel' button in the Main Activity								| Press the 'Cancel' Button												| We go back to the Main Activity page										| Return to the Main Activity page									| Pass		|
| Press the 'Create List' button in the Main Activity				| See behavior of pressing the 'Cancel' button in the Main Activity								| Press the 'Cancel' Button												| We go back to the Main Activity page										| Return to the Main Activity page									| Pass		|
| Create a Reminder without necessary attributes.					| See behavior of trying to create a Reminder without all the necessary attributes.				| Try to create a Reminder with one or more missing attributes.			| A toast notifying of missing attributes.									| A toast notifiying that Description and Type need to be inputted. | Pass		|
| Create a Reminder with all the necessary attributes.				| See behavior of trying to create a Reminder with all the necessary attributes.				| Try to create a Reminder with all attributes.							| A new Reminder with given attributes.										| A new Reminder under the appropriate List.						| Pass		|
| Add Reminder to List with no existing List.                  		| See behavior of trying to add a Reminder to a List when no List exists. 						| Try to Add a Reminder to a List when List doesn't exist. 				| A new List with new Reminder added to it. 								| A new Reminder List created with the Reminder.					| Pass		|
| Add Reminder to List        						        		| See behavior of trying to add a Reminder to a List.					 						| Try to Add a Reminder to a List.										| A new Reminder in given List.												| A new Reminder in the existing Reminder List.						| Pass		|
| Delete Reminder from List       					     	 		| See behavior of trying to delete a Reminder from a List.					 					| Try to delete a Reminder from a List.									| Removal of Reminder in given List.										| The chosen Reminder is deleted from the List.						| Pass		|
| Edit Reminder from List             						 		| See behavior of trying to edit a Reminder in a List.											| Try to edit a Reminder in a List.										| A Reminder in given List with edited attributes.							| WIP																|			|			
| Rename Reminder List              					    		| See behavior of trying to rename a Reminder in a List.										| Try to rename a Reminder in a List.									| A Reminder in given List with new given name.								| WIP																|			|
| Sort Reminder List with no existing Reminders.                    | See behavior of trying to sort a Reminder List when no Reminders exist in the List. 			| Try to sort a Reminder List when there are no Lists. 					| A toast and/or option to create a new List. 								| WIP																|			|
| Sort Reminder List                  								| See behavior of trying to sort a Reminder List.				 								| Try to sort a Reminder List.											| A Reminder List sorted by type.											| WIP																|			|
| Check off a Reminder from a List      							| See behavior of trying to check off a Reminder from a List. 									| Try to check off a Reminder in a List.								| Reminder in given List with isChecked = true								| The chosen Reminder's check attribute is true.					| Pass		|
| Check off all Reminders from a List with no existing Reminder. 	| See behavior of trying to check off all Reminders in a List when no Reminders exists. 		| Try to check off all Reminders in a List when there are no Reminders. | A toast and/or option to create a new Reminder. 							| WIP																|			|
| Check off all Reminders from a List  						 		| See behavior of trying to check off all Reminders in a List.					 				| Try to check off all Reminders in a List.								| All Reminders in given List with isChecked = true							| WIP																|			|
| Clear all check marks from a List with no existing Reminders. 	| See behavior of trying to clear all check marks from a List when no Reminders exist in List. 	| Try to clear all check marks from a List when there are no Reminders. | A toast and/or option to create a new Reminder. 							| WIP																|			|
| Clear all check marks from a List    						  		| See behavior of trying to clear all check marks from a List.  								| Try to clear all check marks from a List.								| All Reminders in given List with isChecked = false						| WIP																|			|
| Test if alarm rings at the selected time and day.					| See if alarm rings at the given time and day.													| Set an alert to ring at a specific time and day and see if it rings.	| A notification of the respective Reminder.								| WIP																|			|
| Test if alarm rings at the given location.						| See if alarm rings when at the given location.												| Set an alert to ring at a specific location and see if it rings. 		| A notification of the respective Reminder.								| WIP																|			|