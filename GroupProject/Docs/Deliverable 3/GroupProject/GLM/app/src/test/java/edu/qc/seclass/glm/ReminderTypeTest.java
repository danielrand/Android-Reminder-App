package edu.qc.seclass.glm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReminderTypeTest {

    private ReminderType remindertype;

    @Before
    public void setUp() throws Exception {
        remindertype = new ReminderType("Appointment");
    }

    @After
    public void tearDown() throws Exception {
        remindertype = null;
    }

    @Test
    public void getType() {
        assertEquals("Appointment", remindertype.getType());
    }

    @Test
    public void setType() {
        remindertype.setType("Shopping");
        assertEquals("Shopping", remindertype.getType());
    }
}