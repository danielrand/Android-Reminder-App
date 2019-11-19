
package edu.qc.seclass.glm;

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<ReminderList> listDataHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ExpandableListView) findViewById(R.id.ExpandLV);
        displayLists();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, MainActivity.this);
        listView.setAdapter(listAdapter);

        findViewById(R.id.createButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateReminderActivity.class);
                startActivityForResult(intent, 1);// Activity is started with requestCode 2
            }
        });


    }


    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 1 && resultCode == 1) { // Returning from Create Reminder activity
            Reminder new_reminder = (Reminder) data.getParcelableExtra("NEW_REMINDER");
            System.out.println("REMINDER RETREIVED:\nType: " + new_reminder.getType().getType() + " Description: " + new_reminder.getDescription());
            createReminder(new_reminder);
        }
        else if (resultCode == 2) { // Return from Edit activity
            Reminder createdReminder = data.getParcelableExtra("NEW_REMINDER");
            int list = data.getIntExtra("LIST",0);
            int child = data.getIntExtra("REMINDER",0);
            createdReminder.setChecked(listDataHeader.get(list).get(child).isChecked());
            listDataHeader.get(list).set(child,createdReminder);
            listAdapter.notifyDataSetChanged();
        }
        else if (requestCode == 3) { // Return from EditReminder activity

        }
    }

    private void createReminder (Reminder new_reminder) {
        ReminderType type = new_reminder.getType();
        ReminderList existingList = null;
        for (ReminderList list: listDataHeader) {
            if (list.getCategoryGroup().equals(type)) {
                existingList = list;
                break;
            }
        }
        if (existingList != null) {
            existingList.addReminder(new_reminder);
            listAdapter.notifyDataSetChanged();
        }
        else {
            ReminderList newList = new ReminderList(new_reminder.getType().getType(), new_reminder.getType());
            newList.addReminder(new_reminder);
            listDataHeader.add(newList);
            listAdapter.notifyDataSetChanged();
        }

    }

    // Displays the list to the main activity screen
    private void displayLists() {
        listDataHeader = new ArrayList<>();

        ReminderType defaultType = new ReminderType("Reminders");
        ReminderList defaultList = new ReminderList("Reminders",defaultType);
        defaultList.addReminder(new Reminder("Buy Groceries",defaultType));
        defaultList.addReminder(new Reminder("Apply to jobs",defaultType));
        listDataHeader.add(defaultList);

        ReminderType hw = new ReminderType("Homework");
        ReminderList homework = new ReminderList("Reminders",hw);
        homework.addReminder(new Reminder("Finish 370 Project",hw));
        homework.addReminder(new Reminder("Finish 316 Project",hw));
        listDataHeader.add(homework);

    }


}
