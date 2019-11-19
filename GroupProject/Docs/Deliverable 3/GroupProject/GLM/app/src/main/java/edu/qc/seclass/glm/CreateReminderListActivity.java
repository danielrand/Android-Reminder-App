package edu.qc.seclass.glm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreateReminderListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_list);

        findViewById(R.id.createListCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
