package edu.qc.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ReminderTest {

    private Reminder reminder;
    private Alert alert;
    private Alert rAlert;
    private Reminder reminderAlert1;
    private Reminder reminderAlert2;
    private ReminderType type;
    private Date date;
    private String message;
    private Repeat repeat;

    @Before
    public void setUp() throws Exception {
        type = new ReminderType("Appointment");
        date = new Date();
        message = "Doctor's Appointment at 3PM";
        repeat = Repeat.NEVER;
        alert = new Alert(date, message);
        rAlert = new Alert(date, message, repeat);
        reminder = new Reminder("Doctor's Appointment", type);
        reminderAlert1 = new Reminder("Doctor's Appointment", type, alert);
        reminderAlert2 = new Reminder("Doctor's Appointment", type, rAlert);
    }

    @After
    public void tearDown() throws Exception {
        type = null;
        date = null;
        message = null;
        repeat = null;
        alert = null;
        rAlert = null;
        reminder = null;
        reminderAlert1 = null;
        reminderAlert2 = null;
    }

    @Test
    public void getDescription() {
        assertEquals("Doctor's Appointment", reminder.getDescription());
    }

    @Test
    public void getDescription2() {
        assertEquals("Doctor's Appointment", reminderAlert1.getDescription());
    }

    @Test
    public void getDescription3() {
        assertEquals("Doctor's Appointment", reminderAlert2.getDescription());
    }

    @Test
    public void setDescription() {
        reminder.setDescription("Grocery Shopping");
        assertEquals("Grocery Shopping", reminder.getDescription());
    }

    @Test
    public void setDescription2() {
        reminderAlert1.setDescription("Grocery Shopping");
        assertEquals("Grocery Shopping", reminderAlert1.getDescription());
    }

    @Test
    public void setDescription3() {
        reminderAlert2.setDescription("Grocery Shopping");
        assertEquals("Grocery Shopping", reminderAlert2.getDescription());
    }

    @Test
    public void getType() {
        assertEquals(type, reminder.getType());
    }

    @Test
    public void getType2() {
        assertEquals(type, reminderAlert1.getType());
    }

    @Test
    public void getType3() {
        assertEquals(type, reminderAlert2.getType());
    }

    @Test
    public void setType() {
        ReminderType newType = new ReminderType("Email");
        reminder.setType(newType);
        assertEquals(newType, reminder.getType());
    }

    @Test
    public void setType2() {
        ReminderType newType = new ReminderType("Email");
        reminderAlert1.setType(newType);
        assertEquals(newType, reminderAlert1.getType());
    }

    @Test
    public void setType3() {
        ReminderType newType = new ReminderType("Email");
        reminderAlert2.setType(newType);
        assertEquals(newType, reminderAlert2.getType());
    }

    @Test
    public void isChecked() {
        assertEquals(false, reminder.isChecked());
    }

    @Test
    public void isChecked2() {
        assertEquals(false, reminderAlert1.isChecked());
    }

    @Test
    public void isChecked3() {
        assertEquals(false, reminderAlert2.isChecked());
    }

    @Test
    public void isCheckedAndSetChecked() {
        reminder.setChecked(true);
        assertEquals(true, reminder.isChecked());
    }

    @Test
    public void isCheckedAndSetChecked2() {
        reminderAlert1.setChecked(true);
        assertEquals(true, reminderAlert1.isChecked());
    }

    @Test
    public void isCheckedAndSetChecked3() {
        reminderAlert2.setChecked(true);
        assertEquals(true, reminderAlert2.isChecked());
    }

    @Test
    public void getAlert() {
        assertEquals(alert, reminderAlert1.getAlert());
    }

    @Test
    public void getAlert2() {
        assertEquals(rAlert, reminderAlert2.getAlert());
    }

    @Test
    public void setAlert() {
        message = "Grocery Shopping at 4PM";
        Date newDate = new Date();
        Alert newAlert = new Alert(newDate, message);
        reminderAlert1.setAlert(newAlert);
        assertEquals(newAlert, reminderAlert1.getAlert());
    }

    @Test
    public void setAlert2() {
        message = "Grocery Shopping at 4PM";
        Date newDate = new Date();
        repeat = Repeat.MONTHLY;
        Alert newAlert = new Alert(newDate, message, repeat);
        reminderAlert2.setAlert(newAlert);
        assertEquals(newAlert, reminderAlert2.getAlert());
    }


    @Test
    public void getReminderID() {
        assertEquals(6, reminder.getReminderID());
    }

    @Test
    public void getReminderID2() {
        assertEquals(1, reminderAlert1.getReminderID());
    }

    @Test
    public void getReminderID3() {
        assertEquals(5, reminderAlert2.getReminderID());
    }
}