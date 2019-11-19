# Test Plan

**Author**: Jeffrey Kim

## 1 Testing Strategy

### 1.1 Overall strategy

-For unit testing, we will first see if we can create Reminders and Lists, which are the core classes of our application, and test them with and without necessary attributes. 
-For integration testing, we will test the methods in the classes that require another class object to exist in order to work. For example, a Reminder can only be added to a List if a List exists, and an Alert can only be made if a Reminder exists. 
-For system testing, we will try recreating a typical user experience: from creating lists and reminders, to testing if the user gets the alert created for a Reminder. 
-For regression testing, we will have to retest all test cases that are related to the change. 

### 1.2 Test Selection

We will select our test cases based on condition testing. We will first test the creation of a List and Reminder with pass and fail conditions. Then we will test the different methods in the classes of our application with pass and fail conditions. Then we will test the creation of Lists, Reminder, and alerts assuming as a user, assuming everything works.

### 1.3 Adequacy Criterion

When Unit testing, we can assess the quality of our test cases by calculating the number of line of code we are executing with the test case divided by the total lines of code in the unit (class) we are testing times 100. When we are Integration and System testing, we can assess the quality of our test cases by calculating the number of lines of code we are executing with the test case divided by the total lines of code in the entire application times 100.

### 1.4 Bug Tracking

We can have a form that a user can fill out to submit bugs and feature requests which will send out an email to the team.

### 1.5 Technology

We will use JUnit and Espresso tests in Android Studio to implement tests. (May use other Technology upon furthur research)

## 2 Test Cases

| Test Case                                                    		| Purpose                           															| Steps Necessary 														| Expected Result 									| Actual Result | Pass/Fail | Add'l Info |
|--------------------------------------------------------------		|-----------------------------------															|-----------------														|-----------------									|---------------|-----------|----------- |
| Create a List without necessary attributes.						| See behavior of trying to create a Reminder List without all the necessary attributes.		| Try to create a Reminder List with one or more missing attributes.	| A toast notifying of missing attributes.			|
| Create a List with all the necessary attributes.					| See behavior of trying to create a Reminder List with all the necessary attributes.			| Try to create a Reminder List with all attributes.					| A new Reminder List with given attributes.		|
| Create a Reminder without necessary attributes.					| See behavior of trying to create a Reminder without all the necessary attributes.				| Try to create a Reminder with one or more missing attributes.			| A toast notifying of missing attributes.			|
| Create a Reminder with all the necessary attributes.				| See behavior of trying to create a Reminder with all the necessary attributes.				| Try to create a Reminder with all attributes.							| A new Reminder with given attributes.				|
| Add Reminder to List with no existing List.                  		| See behavior of trying to add a Reminder to a List when no List exists. 						| Try to Add a Reminder to a List when there are no Lists. 				| A toast and/or option to create a new List. 		|
| Add Reminder to List        						        		| See behavior of trying to add a Reminder to a List.					 						| Try to Add a Reminder to a List.										| A new Reminder in given List.						|
| Delete Reminder from List with no existing List.             		| See behavior of trying to delete a Reminder from a List when no List exists. 					| Try to delete a Reminder from a List when there are no Lists. 		| A toast and/or option to create a new List. 		|
| Delete Reminder from List       					     	 		| See behavior of trying to delete a Reminder from a List.					 					| Try to delete a Reminder from a List.									| Removal of Reminder in given List.				|
| Delete a Reminder with no existing Reminder. 						| See behavior of trying to delete a Reminder when no Reminder exists. 							| Try to delete a Reminder from a List when there are no Reminders. 	| A toast and/or option to create a new Reminder. 	|
| Delete a Reminder								 					| See behavior of trying to delete a Reminder.													| Try to delete a Reminder.												| Removal of Reminder.								|
| Edit Reminder from List with no existing List.               		| See behavior of trying to edit a Reminder in a List when no List exists. 						| Try to edit a Reminder in a List when there are no Lists. 			| A toast and/or option to create a new List. 		|
| Edit Reminder from List             						 		| See behavior of trying to edit a Reminder in a List.											| Try to edit a Reminder in a List.										| A Reminder in given List with edited attributes.	|
| Rename Reminder List with no existing List.                  		| See behavior of trying to rename a Reminder in a List when no List exists. 					| Try to rename a Reminder in a List when there are no Lists. 			| A toast and/or option to create a new List. 		|
| Rename Reminder List              					    		| See behavior of trying to rename a Reminder in a List.										| Try to rename a Reminder in a List.									| A Reminder in given List with new given name.		|
| Rename a Reminder with no existing Reminder. 						| See behavior of trying to rename a Reminder when no Reminder exists. 							| Try to rename a Reminder in a List when there are no Reminders. 		| A toast and/or option to create a new Reminder. 	|
| Rename a Reminder 												| See behavior of trying to rename a Reminder.						 							| Try to rename a Reminder.												| A Reminder with a new given name.					|
| Sort Reminder List with no existing List.                    		| See behavior of trying to sort a Reminder List when no List exists. 							| Try to sort a Reminder List when there are no Lists. 					| A toast and/or option to create a new List. 		|
| Sort Reminder List                  								| See behavior of trying to sort a Reminder List.				 								| Try to sort a Reminder List.											| A Reminder List sorted by type.					|
| Select Reminder List with no existing List.                  		| See behavior of trying to select a Reminder List when no List exists. 						| Try to select a Reminder List when there are no Lists. 				| A toast and/or option to create a new List. 		|
| Select Reminder List                   							| See behavior of trying to select a Reminder List.						 						| Try to select a Reminder List.										| Selection of Reminder List.						|
| Select a Reminder with no existing Reminder. 						| See behavior of trying to select a Reminder when no Reminder exists.							| Try to select a Reminder in a List when there are no Reminders. 		| A toast and/or option to create a new Reminder. 	|
| Select a Reminder 												| See behavior of trying to select a Reminder.													| Try to select a Reminder in a List.									| Selection of Reminder in given List.				|
| Check off a Reminder from a List with no existing List.      		| See behavior of trying to check off a Reminder from a List when no List exists. 				| Try to check off a Reminder in a List when there are no Lists. 		| A toast and/or option to create a new List. 		|
| Check off a Reminder from a List with no existing Reminder.  		| See behavior of trying to check off a Reminder from a List when no Reminder exists. 			| Try to check off a Reminder in a List when there are no Reminders. 	| A toast and/or option to create a new Reminder. 	|
| Check off a Reminder from a List      							| See behavior of trying to check off a Reminder from a List. 									| Try to check off a Reminder in a List.								| Reminder in given List with isChecked = true		|
| Check off all Reminders from a List with no existing List.   		| See behavior of trying to check off all Reminders in a List when no List exists. 				| Try to check off all Reminders in a List when there are no Lists.		| A toast and/or option to create a new List. 		|
| Check off all Reminders from a List with no existing Reminder. 	| See behavior of trying to check off all Reminders in a List when no Reminders exists. 		| Try to check off all Reminders in a List when there are no Reminders. | A toast and/or option to create a new Reminder. 	|
| Check off all Reminders from a List  						 		| See behavior of trying to check off all Reminders in a List.					 				| Try to check off all Reminders in a List.								| All Reminders in given List with isChecked = true	|
| Clear all check marks from a List with no existing List.     		| See behavior of trying to clear all check marks from a List when no List exists. 				| Try to clear all check marks from a List when there are no Lists. 	| A toast and/or option to create a new List. 		|
| Clear all check marks from a List with no existing Reminders. 	| See behavior of trying to clear all check marks from a List when no Reminders exist in List. 	| Try to clear all check marks from a List when there are no Reminders. | A toast and/or option to create a new Reminder. 	|
| Clear all check marks from a List    						  		| See behavior of trying to clear all check marks from a List.  								| Try to clear all check marks from a List.								| All Reminders in given List with isChecked = false|
| Test if alarm rings at the selected time and day.					| See if alarm rings at the given time and day.													| Set an alert to ring at a specific time and day and see if it rings.	| A notification of the respective Reminder.		|
| Test if alarm rings at the given location.						| See if alarm rings when at the given location.												| Set an alert to ring at a specific location and see if it rings. 		| A notification of the respective Reminder.		|