package edu.qc.seclass.glm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// By Daniel Rand, Torendra Rasik, Jeffrey Kim, Jonas Improgo, and Nana Kodjo Acquah

public class MainActivity extends AppCompatActivity{

    public static final int CREATE_REMINDER_REQUEST_CODE = 1;
    public static final int EDIT_REMINDER_REQUEST_CODE = 2;
    Button createButton;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private ArrayList<ReminderList> listDataHeader;
    static ReminderRoomDatabase db;
    private NotificationManagerCompat mNotificationManagerCompat;
    private RelativeLayout mMainRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        db = ReminderRoomDatabase.getDatabase(getApplicationContext());
        listView = (ExpandableListView) findViewById(R.id.ExpandLV);
        displayLists();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, MainActivity.this);
        listView.setAdapter(listAdapter);
        mNotificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        boolean areNotificationsEnabled = mNotificationManagerCompat.areNotificationsEnabled();

        if (!areNotificationsEnabled) {
            // Because the user took an action to create a notification, we create a prompt to let
            // the user re-enable notifications for this application again.
            Snackbar snackbar = Snackbar
                    .make(
                            mMainRelativeLayout,
                            "You need to enable notifications for this app",
                            Snackbar.LENGTH_LONG)
                    .setAction("ENABLE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Links to this app's notification settings
                            openNotificationSettingsForApp();
                        }
                    });
            snackbar.show();
            return;
        }
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createButton.setEnabled(false);
                Intent intent = new Intent(MainActivity.this, CreateReminderActivity.class);
                startActivityForResult(intent, CREATE_REMINDER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (createButton != null)
            createButton.setEnabled(true);
        ExpandableListAdapter.editButtonPressed = false;
        ExpandableListAdapter.createButtonPressed = false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CREATE_REMINDER_REQUEST_CODE || requestCode == 3) && resultCode == RESULT_OK) {
            String descString = data.getStringExtra("DESCRIPTION");
            String typeString = data.getStringExtra("TYPE");
            ReminderType type = new ReminderType(typeString);
            Calendar remCal = (Calendar)data.getSerializableExtra("REMINDER_CALENDAR");
            Reminder newReminder = null;
            db.reminderTypeDao().insert(type);
            if (remCal != null)
                newReminder = createAlert(remCal,data,newReminder,descString,type, requestCode);
            else newReminder = new Reminder(descString, type.getType());
            db.reminderDao().insert(newReminder);
            ReminderList newList = new ReminderList(type.getType());
            if (listDataHeader.contains(newList)) {
                int indexOfList = listDataHeader.indexOf(newList);
                listDataHeader.get(indexOfList).add(newReminder);
                listAdapter.notifyDataSetChanged();
            }
            else {
                newList.add(newReminder);
                listDataHeader.add(0,newList);
                listAdapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == EDIT_REMINDER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Return from Edit activity
                Reminder createdReminder = data.getParcelableExtra("NEW_REMINDER");
                int list = data.getIntExtra("LIST", 0);
                int child = data.getIntExtra("REMINDER", 0);
                String reminderID = createdReminder.getReminderID();
                String desc = createdReminder.getDescription();
                db.reminderDao().updateReminderDescription(reminderID, desc);
                Calendar remCal = (Calendar)data.getSerializableExtra("REMINDER_CALENDAR");
                if (remCal != null)
                    createdReminder = createAlert(remCal, data, createdReminder, desc, new ReminderType(createdReminder.getType()), requestCode);
                db.reminderDao().insert(createdReminder);
                listDataHeader.get(list).set(child, createdReminder);
                listAdapter.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    // Displays the list to the main activity screen by pulling reminders from the database
    private void displayLists() {
        listDataHeader = new ArrayList<>();
        List<ReminderType> typeList = db.reminderTypeDao().getAlphabetizedTypes();
        for (ReminderType type: typeList) {
            List<Reminder> list = db.reminderDao().getAllRemindersOfType(type.getType());
            ReminderList reminderList = new ReminderList(type.getType());
            for (Reminder r: list)
                reminderList.add(r);
            listDataHeader.add(reminderList);
        }
    }

    private void openNotificationSettingsForApp() {
        // Links to this app's notification settings.
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        //for Android 5-7
        intent.putExtra("app_package", getPackageName());
        intent.putExtra("app_uid", getApplicationInfo().uid);
        // for Android 8 and above
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        startActivity(intent);
    }

//    public void expandAll() {
//        int s = listAdapter.getGroupCount();
//        for (int i = 0; i < s; i++) {
//            listView.expandGroup(i);
//        }
//    }

    static int notificationId = 0;

    private void startAlarm(Calendar c, Reminder r, String repeat) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_icon_bell_round) // notification icon
                .setContentTitle(r.getType()) // title for notification
                .setContentText(r.getDescription())// message for notification
                .setAutoCancel(true);// clear notification after click
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        Notification notification = mBuilder.build();
        Intent notificationIntent = new Intent(this, AlertReceiver.class);
        notificationIntent.putExtra(AlertReceiver.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(AlertReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Mapping of repeat String settings to their appropriate millisecond repeat delays
        HashMap <String,Long> repeatToMilliDelay = new HashMap<>();
        repeatToMilliDelay.put("Once", AlarmManager.INTERVAL_DAY);
        repeatToMilliDelay.put("Daily", AlarmManager.INTERVAL_DAY);
        repeatToMilliDelay.put("Weekly", 1000L*60L*60L*24L*7L);
        Calendar monthLater = (Calendar)c.clone();
        monthLater.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
        repeatToMilliDelay.put("Monthly", monthLater.getTimeInMillis()-c.getTimeInMillis());
        Calendar yearLater = (Calendar)c.clone();
        yearLater.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
        repeatToMilliDelay.put("Yearly", yearLater.getTimeInMillis()-c.getTimeInMillis());

        if (repeat.equalsIgnoreCase("Never"))
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        else alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), repeatToMilliDelay.get(repeat), pendingIntent);
        notificationId++;
    }

    private Reminder createAlert (Calendar remCal, Intent data, Reminder newReminder, String descString, ReminderType type, int requestCode) {
        Date remDate = remCal.getTime();
        String repeat = data.getStringExtra("REPEAT");
        Alert alert = new Alert(remDate,repeat);
        boolean checked = false;
        if (requestCode == 2) {
            Alert oldAlert = MainActivity.db.alertDao().getAlertByID(newReminder.getAlertID());
            if (oldAlert == null || !alert.equals(oldAlert)) {
                db.alertDao().insert(alert);
                db.reminderDao().deleteReminderbyID(newReminder.getReminderID());
                checked = newReminder.isChecked();
            }
            else return newReminder;
        }
        else db.alertDao().insert(alert);
        newReminder = new Reminder(descString, type.getType(), alert.getAlertID());
        newReminder.setChecked(checked);
        Calendar alertTime = Calendar.getInstance();
        alertTime.setTime(alert.getAlertTime());
        startAlarm(alertTime, newReminder, repeat);
        return newReminder;
    }
}
