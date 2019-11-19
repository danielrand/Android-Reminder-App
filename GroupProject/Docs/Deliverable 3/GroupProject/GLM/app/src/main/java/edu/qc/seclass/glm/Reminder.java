package edu.qc.seclass.glm;

import android.os.Parcel;
import android.os.Parcelable;

public class Reminder implements Comparable, Parcelable{

    private String description;
    private ReminderType type;
    private boolean isChecked = false;
    private Alert alert;
    private static int reminderCounter = 0;
    private final int reminderID;

    public Reminder(String description, ReminderType type) {
        this.description = description;
        this.type = type;
        alert = null;
        reminderID = reminderCounter++;
        System.out.println(reminderID + " " + reminderCounter);
    }


    public Reminder(String description, ReminderType type, Alert alert) {
        this.description = description;
        this.type = type;
        this.alert = alert;
        reminderID = reminderCounter++;
        System.out.println(reminderID + " " + reminderCounter);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReminderType getType() {
        return type;
    }

    public void setType(ReminderType type) {
        this.type = type;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public int getReminderID() {
        return reminderID;
    }

    @Override
    public int compareTo(Object o) {
        return this.description.compareToIgnoreCase(((Reminder)o).description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(description);
        out.writeValue(type);
        out.writeInt(isChecked ? 1 : 0);
        out.writeValue(alert);
        out.writeInt(reminderCounter);
        out.writeInt(reminderID);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Reminder> CREATOR = new Parcelable.Creator<Reminder>() {
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Reminder(Parcel in) {
        description = in.readString();
        type = (ReminderType)in.readValue(ReminderType.class.getClassLoader());
        isChecked = (in.readInt() == 1);
        alert = (Alert)in.readValue(Alert.class.getClassLoader());
        reminderCounter = in.readInt();
        reminderID = in.readInt();
    }
}
