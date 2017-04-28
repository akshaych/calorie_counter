package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    protected String monthyear;
    protected JSONArray dateInfo;
    protected String weight, breakfast, lunch, dinner, snack1, snack2, snack3;
    protected String breakfastCalories, lunchCalories, dinnerCalories, snack1Calories, snack2Calories, snack3Calories;

    protected EditText editText_weight;
    protected EditText editText_breakfast;
    protected EditText editText_lunch;
    protected EditText editText_dinner;
    protected EditText editText_snack1;
    protected EditText editText_snack2;
    protected EditText editText_snack3;

    protected EditText editText_breakfastCalories;
    protected EditText editText_lunchCalories;
    protected EditText editText_dinnerCalories;
    protected EditText editText_snack1Calories;
    protected EditText editText_snack2Calories;
    protected EditText editText_snack3Calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        foodView = new FoodView(this);
        date = getIntent().getExtras().getString("DATE");
        monthyear = getIntent().getExtras().getString("MONTHYEAR");
        TextView myView = (TextView) findViewById(R.id.textView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date);
        myref_date.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String s = dataSnapshot.getValue(String.class);
                try {
                    if (s == null) {
                        String[] arr = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
                        dateInfo = new JSONArray(arr);
                    } else {
                        dateInfo = new JSONArray(s);
                    }
                    editText_weight = (EditText) findViewById(R.id.weight);
                    editText_breakfast = (EditText) findViewById(R.id.breakfast);
                    editText_lunch = (EditText) findViewById(R.id.lunch);
                    editText_dinner = (EditText) findViewById(R.id.dinner);
                    editText_snack1 = (EditText) findViewById(R.id.snack1);
                    editText_snack2 = (EditText) findViewById(R.id.snack2);
                    editText_snack3 = (EditText) findViewById(R.id.snack3);

                    editText_breakfastCalories = (EditText) findViewById(R.id.breakfastCalories);
                    editText_lunchCalories = (EditText) findViewById(R.id.lunchCalories);
                    editText_dinnerCalories = (EditText) findViewById(R.id.dinnerCalories);
                    editText_snack1Calories = (EditText) findViewById(R.id.snack1Calories);
                    editText_snack2Calories = (EditText) findViewById(R.id.snack2Calories);
                    editText_snack3Calories = (EditText) findViewById(R.id.snack3Calories);

                    editText_weight.setText((String) dateInfo.get(0));

                    editText_breakfast.setText((String) dateInfo.get(1));
                    editText_breakfastCalories.setText((String) dateInfo.get(2));

                    editText_lunch.setText((String) dateInfo.get(3));
                    editText_lunchCalories.setText((String) dateInfo.get(4));

                    editText_dinner.setText((String) dateInfo.get(5));
                    editText_dinnerCalories.setText((String) dateInfo.get(6));

                    editText_snack1.setText((String) dateInfo.get(7));
                    editText_snack1Calories.setText((String) dateInfo.get(8));

                    editText_snack2.setText((String) dateInfo.get(9));
                    editText_snack2Calories.setText((String) dateInfo.get(10));

                    editText_snack3.setText((String) dateInfo.get(11));
                    editText_snack3Calories.setText((String) dateInfo.get(12));

                } catch (JSONException e) {
                    Toast.makeText(FoodActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date);

        editText_weight = (EditText) findViewById(R.id.weight);
        weight = editText_weight.getText().toString();

        editText_breakfast = (EditText) findViewById(R.id.breakfast);
        breakfast = editText_breakfast.getText().toString();

        editText_breakfastCalories = (EditText) findViewById(R.id.breakfastCalories);
        breakfastCalories = editText_breakfastCalories.getText().toString();

        editText_lunch = (EditText) findViewById(R.id.lunch);
        lunch = editText_lunch.getText().toString();

        editText_lunchCalories = (EditText) findViewById(R.id.lunchCalories);
        lunchCalories = editText_lunchCalories.getText().toString();

        editText_dinner = (EditText) findViewById(R.id.dinner);
        dinner = editText_dinner.getText().toString();

        editText_dinnerCalories = (EditText) findViewById(R.id.dinnerCalories);
        dinnerCalories = editText_dinnerCalories.getText().toString();

        editText_snack1 = (EditText) findViewById(R.id.snack1);
        snack1 = editText_snack1.getText().toString();

        editText_snack1Calories = (EditText) findViewById(R.id.snack1Calories);
        snack1Calories = editText_snack1Calories.getText().toString();

        editText_snack2 = (EditText) findViewById(R.id.snack2);
        snack2 = editText_snack2.getText().toString();

        editText_snack2Calories = (EditText) findViewById(R.id.snack2Calories);
        snack2Calories = editText_snack2Calories.getText().toString();

        editText_snack3 = (EditText) findViewById(R.id.snack3);
        snack3 = editText_snack3.getText().toString();

        editText_snack3Calories = (EditText) findViewById(R.id.snack3Calories);
        snack3Calories = editText_snack3Calories.getText().toString();

        try {
            dateInfo.put(0, weight);
            dateInfo.put(1, breakfast);
            dateInfo.put(2, breakfastCalories);
            dateInfo.put(3, lunch);
            dateInfo.put(4, lunchCalories);
            dateInfo.put(5, dinner);
            dateInfo.put(6, dinnerCalories);
            dateInfo.put(7, snack1);
            dateInfo.put(8, snack1Calories);
            dateInfo.put(9, snack2);
            dateInfo.put(10, snack2Calories);
            dateInfo.put(11, snack3);
            dateInfo.put(12, snack3Calories);
            myref_date.setValue(dateInfo.toString());
            Toast.makeText(foodView.getContext(),
                    "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CalendarActivity.class);
            i.putExtra("Type","calendar");
            startActivity(i);
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error2", Toast.LENGTH_SHORT).show();
        }
    }

}