package edu.qc.seclass.glm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ReminderList> listDataHeader;
    Activity mainActivity;
    public static boolean editButtonPressed = false;

    private final Set<Pair<Long, Long>> tagCheckedItems = new HashSet<>();

    public ExpandableListAdapter(Context context, List<ReminderList> listDataHeader, Activity mainActivity) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataHeader.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition).getType();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataHeader.get(groupPosition).get(childPosition).getWord();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reminder_list, null);
        }

        TextView remListTitle = (TextView) convertView.findViewById(R.id.remListTitle);
        remListTitle.setTypeface(null, Typeface.BOLD);
        remListTitle.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reminder_item, null);
        }
        Button deleteBtn = (Button) convertView.findViewById(R.id.btnDelete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Reminder oldReminder = listDataHeader.get(groupPosition).remove(childPosition);
                MainActivity.db.reminderDao().deleteReminderbyID(oldReminder.getReminderID());
                notifyDataSetChanged();
            }
        });
        final Button editBtn = (Button) convertView.findViewById(R.id.btnEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!editButtonPressed) {
                    editButtonPressed = true;
                    Reminder selectedReminder = listDataHeader.get(groupPosition).get(childPosition);
                    Intent intent = new Intent(mainActivity, EditReminderActivity.class);
                    intent.putExtra("SELECTED_REMINDER", selectedReminder);
                    intent.putExtra("LIST", groupPosition);
                    intent.putExtra("REMINDER",childPosition);
                    mainActivity.startActivityForResult(intent,2);
                }
            }
        });
        TextView tvListChild = (TextView) convertView.findViewById(R.id.remItemName);
        tvListChild.setText(childText);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Set<Pair<Long, Long>> getCheckedItems() {
        return tagCheckedItems;
    }
}
