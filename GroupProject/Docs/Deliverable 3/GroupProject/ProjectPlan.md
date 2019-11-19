# Project Plan

**Author**: Daniel Rand

## 1. Introduction

The product we are developing is a simple and easy to use Android application to manage tasks that a user wants to be reminded of.  The key feature of the app is the orginization of reminders into heirarchical lists grouped by category.

## 2. Process Description

### Main Activity:

**Description:**

- This is the activity the user is greeted with upon opening the app. It contains ther user's heirarchical reminder lists grouped by type and is the main medium through which the user interacts with the application. It allows to user to perform actions such as expand lists, view reminders, edit reminders, delete reminders, create reminders, etc., opening new activites if necessary.

**Entrance Criteria**

- This is the default actvity. It is opens immediately upon running the application, so is not reliant on input.

**Exit Criteria**
- None, see above.

### Create Reminder Activity:

**Description:**

- This activity is prompted by the main activity when the user desires to create a new reminder. It will allow the user create a reminder and edit all features of a reminder such as alerts, description, name, etc. 

**Entrance Criteria**

- If being added to a specific Reminder the input will be that reminder list.

**Exit Criteria**
- The output will be a new Reminder. 
- We know if it is completed satisfactorily if the Reminder is valid, meaning all necessary fields are given values.

### Edit Reminder Activity:

**Description:**

- This activity is prompted by the main activity when the user desires to edit a existing reminder. It will allow the user to edit all features of a reminder such as alerts, description, name, category, etc.

**Entrance Criteria**

- Input to the activity would be an existing reminder that the user selected from the list in order to edit.

**Exit Criteria**
- The output will be the same reminder, but with its attributes altered to the preferences of the user.
- We know if it is completed satisfactorily if the Reminder is valid, meaning all necessary fields are given values.

## 3. Team
**People:**
- Torendra Rasik
- Daniel Rand
- Jeffrey Kim
- Jonas Improgo
- Nana Kodjo Acquah

**Roles:**
- User Interface Designer
	- Responsible for designing the layout and appearance of the Application's user interface.
- Frontend Engineer
	- Responsible for implementing Android user interface specified by the User Interface Designer.
- Backend Engineer
	- Responsible implementing the various functions of the application on the backend in Java as specificed by the UML class diagram. 
	- Provides the functionality to the user interface.
- Database Engineer
	- Responsible for designing, implementing, and maintaining the database. 
	- Will communicate with the Backend Engineer regarding incorporation of database querying into the backend. 
- Test Engineer
	- Responsible for designing test procedure, strategy, individual test cases, and framework selection.
- Documentation
	- Responsible for documenting application structure and use guides.
- Project Manager
	- Responsible for project orginization and delivery.


| Team Member       | Roles                             |
|-------------------|-----------------------------------|
| Daniel Rand       | Project Manager, Backend Engineer, Database Engineer|
| Torendra Rasik    | Test Engineer, Documentation      |
| Jeffrey Kim       | Frontend Engineer, Test Engineer  |
| Jonas Improgo     | User Interface Designer, Frontend Engineer, Backend Engineer|
| Nana Kodjo Acquah | Database Engineer, Documentation  |
