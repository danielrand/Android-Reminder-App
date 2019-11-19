package edu.qc.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AlertTest {

    private Alert alert;
    private Alert alertRepeat;
    private Date date;
    private String message;
    private Repeat repeat;

    @Before
    public void setUp() throws Exception {
        date = new Date();
        message = "Doctor's Appointment at 3PM";
        repeat = Repeat.NEVER;
        alert = new Alert(date, message);
        alertRepeat = new Alert(date, message, repeat);
    }

    @After
    public void tearDown() throws Exception {
        date = null;
        message = null;
        alert = null;
        repeat = null;
        alertRepeat = null;
    }

    @Test
    public void getAlertTime() {
        assertEquals(date, alert.getAlertTime());
    }

    @Test
    public void getAlertTime2() {
        assertEquals(date, alertRepeat.getAlertTime());
    }

    @Test
    public void setAlertTime() {
        Date newDate = new Date();
        alert.setAlertTime(newDate);
        assertEquals(newDate, alert.getAlertTime());
    }

    @Test
    public void setAlertTime2() {
        Date newDate = new Date();
        alertRepeat.setAlertTime(newDate);
        assertEquals(newDate, alertRepeat.getAlertTime());
    }

    @Test
    public void getMessage() {
        assertEquals("Doctor's Appointment at 3PM", alert.getMessage());
    }

    @Test
    public void getMessage2() {
        assertEquals("Doctor's Appointment at 3PM", alertRepeat.getMessage());
    }

    @Test
    public void setMessage() {
        alert.setMessage("Test Kojo at 6PM about project");
        assertEquals("Test Kojo at 6PM about project", alert.getMessage());
    }

    @Test
    public void setMessage2() {
        alertRepeat.setMessage("Test Kojo at 6PM about project");
        assertEquals("Test Kojo at 6PM about project", alertRepeat.getMessage());
    }

    @Test
    public void getRepeat() {
        assertEquals(repeat, alert.getRepeat());
    }

    @Test
    public void getRepeat2() {
        assertEquals(repeat, alertRepeat.getRepeat());
    }

    @Test
    public void setRepeat() {
        repeat = Repeat.WEEKLY;
        alert.setRepeat(repeat);
        assertEquals(repeat, alert.getRepeat());
    }

    @Test
    public void setRepeat2() {
        repeat = Repeat.WEEKLY;
        alertRepeat.setRepeat(repeat);
        assertEquals(repeat, alertRepeat.getRepeat());
    }
}