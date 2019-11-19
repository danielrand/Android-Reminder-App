package edu.qc.seclass.glm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditReminderActivity extends Activity {

    Reminder selectedReminder;
    EditText desc, type;
    Alert alert;
    String descString, typeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminder);
        selectedReminder = getIntent().getParcelableExtra("SELECTED_REMINDER");
        populateFields();
        findViewById(R.id.editReminderCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.editReminderDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidReminder()) {
                    alert = selectedReminder.getAlert();
                    selectedReminder.setDescription(descString);
                    selectedReminder.setType(new ReminderType(typeString));
                    selectedReminder.setAlert(alert);
                    Intent intent=new Intent();
                    intent.putExtra("NEW_REMINDER",selectedReminder);
                    int list = getIntent().getIntExtra("LIST",0);
                    int child = getIntent().getIntExtra("REMINDER",0);
                    intent.putExtra("LIST", list);
                    intent.putExtra("REMINDER", child);
                    setResult(2,intent);
                    finish();//finishing activity
                }
                else showToast("Please enter values for Description and Type");
            }
        });
    }

    private void populateFields () {
        desc = findViewById(R.id.editInputDescription);
        type = findViewById(R.id.editInputType);
        desc.setText(selectedReminder.getDescription());
        type.setText(selectedReminder.getType().getType());
    }

    public boolean isValidReminder () {
        descString = desc.getText().toString();
        typeString = type.getText().toString();
        return !(descString.equals("") || typeString.equals(""));
    }

    private void showToast (String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
