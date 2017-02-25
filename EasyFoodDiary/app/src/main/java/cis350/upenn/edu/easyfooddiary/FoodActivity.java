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
 * Created by haile on 2/24/2017.
 */

public class FoodActivity extends AppCompatActivity {

    protected FoodView foodView;
    protected String date;
    protected JSONArray dateInfo;
    protected String weight, breakfast, lunch, dinner, snack;

    protected EditText editText_weight;
    protected EditText editText_breakfast;
    protected EditText editText_lunch;
    protected EditText editText_dinner;
    protected EditText editText_snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        foodView = new FoodView(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date);
        myref_date.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String s = dataSnapshot.getValue(String.class);
                try {
                    dateInfo = new JSONArray(s);
                } catch (JSONException e) {
                    Toast.makeText(FoodActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
        editText_weight = (EditText) findViewById(R.id.weight);
        editText_breakfast = (EditText) findViewById(R.id.breakfast);
        editText_lunch = (EditText) findViewById(R.id.lunch);
        editText_dinner = (EditText) findViewById(R.id.dinner);
        editText_snack = (EditText) findViewById(R.id.snack);

        try {
            editText_weight.setText((String) dateInfo.get(0));
            editText_breakfast.setText((String) dateInfo.get(1));
            editText_lunch.setText((String) dateInfo.get(2));
            editText_dinner.setText((String) dateInfo.get(3));
            editText_snack.setText((String) dateInfo.get(4));
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date);

        editText_weight = (EditText) findViewById(R.id.weight);
        weight = editText_weight.getText().toString();

        editText_breakfast = (EditText) findViewById(R.id.breakfast);
        breakfast = editText_breakfast.getText().toString();

        editText_lunch = (EditText) findViewById(R.id.lunch);
        lunch = editText_lunch.getText().toString();

        editText_dinner = (EditText) findViewById(R.id.dinner);
        dinner = editText_dinner.getText().toString();

        editText_snack = (EditText) findViewById(R.id.snack);
        snack = editText_snack.getText().toString();

        try {
            dateInfo.put(0, weight);
            dateInfo.put(1, breakfast);
            dateInfo.put(2, lunch);
            dateInfo.put(3, dinner);
            dateInfo.put(4, snack);
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        myref_date.setValue(dateInfo);

        Toast.makeText(foodView.getContext(),
                "Saved", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
    }

}