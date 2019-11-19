package edu.qc.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ReminderListTest {

    private ReminderList reminderlist;
    private String name;
    private ReminderType category;

    @Before
    public void setUp() throws Exception {
        name = "Weekly Reminders";
        category = new ReminderType("Shopping");
        reminderlist = new ReminderList(name, category);
    }

    @After
    public void tearDown() throws Exception {
        name = null;
        category = null;
        reminderlist = null;
    }

    @Test
    public void sortListByNameAndAddReminder() {
        Alert alert1 = new Alert(new Date(),"Doctor's Appointment at 6PM");
        Alert alert2 = new Alert(new Date(),"Therapist Appointment at 2PM", Repeat.WEEKLY);
        ReminderType type = new ReminderType("Appointment");
        Reminder reminder1 = new Reminder("Doctor's Appointment", type);
        Reminder reminder2 = new Reminder("Checkup Appointment", type, alert1);
        Reminder reminder3 = new Reminder("Therapist's Appointment", type, alert2);
        reminderlist.addReminder(reminder1);
        reminderlist.addReminder(reminder2);
        reminderlist.addReminder(reminder3);
        reminderlist.sortListByName();
        Iterator ri = reminderlist.iterator();
        assertEquals(reminder2 ,ri.next());
        assertEquals(reminder1 ,ri.next());
        assertEquals(reminder3 ,ri.next());

    }
}