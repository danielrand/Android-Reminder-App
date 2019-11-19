package edu.qc.seclass.glm;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class CreateReminderActivityTest {

    @Rule
    public ActivityTestRule<CreateReminderActivity> mActivityTestRule = new ActivityTestRule<>(CreateReminderActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void CancelButtonTest() {
        onView(withId(R.id.createReminderCancel)).perform(click());
        assertEquals(0, mActivityTestRule.getActivityResult().getResultCode());
        //intended(hasComponent(MainActivity.class.getName())); // NOT WORKING
    }

    @Test
    public void ValidInputTest() {
        onView(withId(R.id.inputType)).perform(typeText("Reminders"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Do Testing"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        assertEquals(1, mActivityTestRule.getActivityResult().getResultCode());
        /*Reminder reminder = new Reminder("Do Testing", new ReminderType("Reminders"));
        Intent resultData = new Intent();
        resultData.putExtra("NEW_REMINDER", reminder);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        main.launchActivity(resultData);
         */
        // INCOMPLETE
    }

    @Test
    public void InvalidInputTest1() {
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void InvalidInputTest2() {
        onView(withId(R.id.inputType)).perform(typeText("Homework"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void InvalidInputTest3() {
        onView(withId(R.id.inputDescription)).perform(typeText("CS 370 HW"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
