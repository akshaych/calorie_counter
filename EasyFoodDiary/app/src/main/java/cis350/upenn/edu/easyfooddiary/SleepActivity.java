package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by vamsee on 4/27/17.
 */

public class SleepActivity extends AppCompatActivity{

    protected SleepView sleepView;
    protected String date;
    protected String monthyear;
    protected String bedtime, hours;
    protected JSONArray dateInfo;

    protected EditText editText_bedtime;
    protected EditText editText_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        sleepView = new SleepView(this);
        date = getIntent().getExtras().getString("DATE");
        monthyear = getIntent().getExtras().getString("MONTHYEAR");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date + "s");
        myref_date.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                try {
                    if (s == null) {
                        String[] arr = {"", ""};
                        dateInfo = new JSONArray(arr);
                    } else {
                        dateInfo = new JSONArray(s);
                    }
                    editText_bedtime = (EditText) findViewById(R.id.bedtime);
                    editText_hours = (EditText) findViewById(R.id.hours);

                    editText_bedtime.setText((String) dateInfo.get(0));
                    editText_hours.setText((String) dateInfo.get(1));

                } catch (JSONException e) {
                    Toast.makeText(SleepActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                }

            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date + "s");
        editText_bedtime = (EditText) findViewById(R.id.bedtime);
        bedtime = editText_bedtime.getText().toString();

        editText_hours = (EditText) findViewById(R.id.hours);
        hours = editText_hours.getText().toString();

        try {
            dateInfo.put(0, bedtime);
            dateInfo.put(1, hours);
            myref_date.setValue(dateInfo.toString());
            Toast.makeText(sleepView.getContext(),
                    "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","sleep");
            startActivity(i);

        } catch (JSONException e) {
            Toast.makeText(SleepActivity.this, "Error2", Toast.LENGTH_SHORT).show();
        }

    }
}

