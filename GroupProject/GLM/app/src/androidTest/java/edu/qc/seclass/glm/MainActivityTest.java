package edu.qc.seclass.glm;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);

    @After
    public void tearDown(){
        try {
            onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
            onView(withText("Delete List")).perform(click());
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
        }

        try {
            onView(withId(R.id.createReminderCancel)).perform(click());
            onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
            onView(withText("Delete List")).perform(click());
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
        }

        try {
            onView(withId(R.id.createReminderCancel)).perform(click());
            onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
            onView(withText("Delete List")).perform(click());
        } catch (NoMatchingViewException e) {
            // View is not in hierarchy
        }
    }

    @Test
    public void createButtonOpensCreateReminderActivity() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId((R.id.createReminderTitle))).check(matches(withText("Create Reminder")));
    }

    @Test
    public void AddReminderListTestInMain() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Doctor's Appointment"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText(startsWith("Doctor's Appointment")))).perform(click());
    }

    @Test
    public void AddReminderWithAlertTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.inputTimeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.inputDateButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 12, 31));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText(startsWith("Test Reminder")))).perform(click());
    }

    @Test
    public void AddReminderToExistingListTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
        onView(withText("Create New Reminder")).perform(click());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder 2"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText("Test Reminder"))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText("Test Reminder 2"))).perform(click());
    }

    @Test
    public void EditReminderActivityLaunchTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.btnEdit), hasSibling(withText("Test Reminder")))).perform(click());
        onView(withId((R.id.createReminderTitle))).check(matches(withText("Edit Reminder")));
    }

    @Test
    public void EditDescriptionInExistingReminderTest(){
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.btnEdit), hasSibling(withText("Test Reminder")))).perform(click());
        onView(withId((R.id.createReminderTitle))).check(matches(withText("Edit Reminder")));

        onView(withId(R.id.inputDescription)).perform(replaceText("Changed Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(allOf(withId(R.id.remItemName), withText(startsWith("Changed Reminder")))).perform(click());
    }

    @Test
    public void EditAlertInExistingReminderTest(){
        onView(withId(R.id.createButton)).perform(click());
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

        onView(allOf(withId(R.id.btnEdit), hasSibling(withText("Do Testing for 370")))).perform(click());
        onView(withId((R.id.createReminderTitle))).check(matches(withText("Edit Reminder")));
        onView(withId(R.id.inputTimeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.inputDateButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 1, 1));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.createReminderDone)).perform(click());
        onView(allOf(withId(R.id.remAlert), hasSibling(withText("Do Testing for 370")), withText(startsWith("Alert: Wed, Jan 01 2020 at 12:30 PM")))).perform(click());
    }

    @Test
    public void CheckAllTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder 2"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder 3"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.checkAllButton), hasSibling(withText("Test")))).perform(click());
        onView(allOf(withId(R.id.remItem), hasSibling(withText("Test Reminder")))).check(matches(isChecked()));
        onView(allOf(withId(R.id.remItem), hasSibling(withText("Test Reminder 2")))).check(matches(isChecked()));
        onView(allOf(withId(R.id.remItem), hasSibling(withText("Test Reminder 3")))).check(matches(isChecked()));
    }

    @Test
    public void DeleteReminderTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.remListTitle), withText(startsWith("Test")))).perform(click());
        onView(allOf(withId(R.id.btnDelete), hasSibling(withText("Test Reminder")))).perform(click());
        onView(allOf(withId(R.id.remItemName), withText("Test Reminder"), withParent(withText("Test")))).check(doesNotExist());
    }

    @Test
    public void DeleteListTest() {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.inputType)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.inputDescription)).perform(typeText("Test Reminder"), closeSoftKeyboard());
        onView(withId(R.id.createReminderDone)).perform(click());

        onView(allOf(withId(R.id.listButton), hasSibling(withText("Test")))).perform(click());
        onView(withText("Delete List")).perform(click());
        onView(allOf(withId(R.id.remListTitle), (withText("Test")))).check(doesNotExist());
    }
}
