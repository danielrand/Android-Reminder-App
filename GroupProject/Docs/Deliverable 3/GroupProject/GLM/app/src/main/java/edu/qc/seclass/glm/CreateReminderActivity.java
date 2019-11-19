package edu.qc.seclass.glm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateReminderActivity extends Activity {

    EditText desc, type;
    String descString, typeString;
    Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_reminder);
        findViewById(R.id.createReminderCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

        findViewById(R.id.createReminderDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidReminder()) {
                    reminder = new Reminder(descString, new ReminderType(typeString));
                    // TODO: pass reminder to to main activity
                    Intent intent=new Intent();
                    intent.putExtra("NEW_REMINDER",reminder);
                    setResult(1,intent);
                    finish();//finishing activity
                }
                else showToast("Please enter values for Description and Type");
            }
        });
    }

    private void showToast (String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean isValidReminder () {
        desc = findViewById(R.id.inputDescription);
        descString = desc.getText().toString();
        type = findViewById(R.id.inputType);
        typeString = type.getText().toString();
        return !(descString.equals("") || typeString.equals(""));
    }

    public void createReminder () {
        System.out.println("Is Valid");
    }

}
