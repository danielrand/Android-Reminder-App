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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CreateReminderListActivityTest {

    @Rule
    public ActivityTestRule<CreateReminderListActivity> mActivityTestRule = new ActivityTestRule<>(CreateReminderListActivity.class);

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
        onView(withId(R.id.createListCancel)).perform(click());
        assertEquals(0, mActivityTestRule.getActivityResult().getResultCode());
        //intended(hasComponent(MainActivity.class.getName())); // NOT WORKING
    }

    @Test
    public void ValidInputTest() {
        onView(withId(R.id.inputListName)).perform(typeText("Homework"), closeSoftKeyboard());
        onView(withId(R.id.createListDone)).perform(click());

        //INCOMPLETE
    }

    @Test
    public void InvalidInputTest() {
        onView(withId(R.id.createListDone)).perform(click());
        //onView(withText(toast)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        //INCOMPLETE - NOT IMPLEMENTED YET
    }
}
