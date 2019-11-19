package edu.qc.seclass.glm;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(JUnit4.class)
public class CreateReminderActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId((R.id.createReminderTitle))).check(matches(withText("Create Reminder")));
    }

    @After
    public void tearDown(){
        try {
            onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
            onView(withText("Delete List")).perform(click());
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
        }
    }

    @Test
    public void CancelButtonTest() {
        onView(withId(R.id.createReminderCancel)).perform(click());
        onView(withId(R.id.createButton)).check(matches(isDisplayed()));
    }

    @Test
    public void ValidInputTest() {
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Do Testing for 370"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText(startsWith("Do Testing for 370")))).perform(click());
    }

    @Test
    public void InvalidInputTest1() {
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mainRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void InvalidInputTest2() {
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mainRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void InvalidInputTest3() {
        onView(withId(R.id.inputDescription)).perform(typeText("CS 370 HW"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("Please enter values for Description and Type")).inRoot(withDecorView(not(is(mainRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void ValidAlertInputTest() {
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Do Testing for 370"), closeSoftKeyboard());
        onView(withId(R.id.inputTimeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.inputDateButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 12, 31));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.remAlert), hasSibling(withText("Do Testing for 370")), withText(startsWith("Alert: Tue, Dec 31 2019 at 6:30 AM")))).perform(click());
    }

    @Test
    public void InvalidAlertInputTest1() {
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Do Testing for 370"), closeSoftKeyboard());
        onView(withId(R.id.inputTimeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("If you'd like to set an alert make sure you enter a time and a date")).inRoot(withDecorView(not(is(mainRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void InvalidAlertInputTest2() {
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Do Testing for 370"), closeSoftKeyboard());
        onView(withId(R.id.inputDateButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 12, 31));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(withText("If you'd like to set an alert make sure you enter a time and a date")).inRoot(withDecorView(not(is(mainRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
