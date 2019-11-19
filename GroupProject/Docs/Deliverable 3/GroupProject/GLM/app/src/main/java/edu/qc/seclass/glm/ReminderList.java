package edu.qc.seclass.glm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ReminderList implements Iterable<Reminder>{

    private ArrayList <Reminder> reminderList;
    private ReminderType categoryGroup;
    private String name;

    public ReminderList(String name, ReminderType categoryGroup) {
        this.categoryGroup = categoryGroup;
        this.name = name;
        reminderList = new ArrayList<>();
    }

    public void sortListByName () {
        Collections.sort(reminderList);
    }

    public void addReminder (Reminder r) {
        reminderList.add(0,r);
    }

    @Override
    public Iterator iterator() {
        return reminderList.iterator();
    }

    public int size () {
        return reminderList.size();
    }

    public Reminder get (int i) {
        return reminderList.get(i);
    }

    public ReminderType getCategoryGroup() {
        return categoryGroup;
    }

    public void remove (int i) {reminderList.remove(i);}

    public void set (int i, Reminder r) {reminderList.set(i,r);}

}
