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
    protected String monthyear;
    protected JSONArray avgWeight;
    protected JSONArray dateInfo;
    protected String weight, breakfast, lunch, dinner, snack;
    protected int avg, denom;
    protected boolean initWeightEmpty;
    protected String breakfastCalories, lunchCalories, dinnerCalories, snackCalories;

    protected EditText editText_weight;
    protected EditText editText_breakfast;
    protected EditText editText_lunch;
    protected EditText editText_dinner;
    protected EditText editText_snack;

    protected EditText editText_breakfastCalories;
    protected EditText editText_lunchCalories;
    protected EditText editText_dinnerCalories;
    protected EditText editText_snackCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        foodView = new FoodView(this);
        date = getIntent().getExtras().getString("DATE");
        monthyear = getIntent().getExtras().getString("MONTHYEAR");
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
                        String[] arr = {"", "", "", "", "", "", "", "", ""};
                        dateInfo = new JSONArray(arr);
                    } else {
                        dateInfo = new JSONArray(s);
                    }
                    editText_weight = (EditText) findViewById(R.id.weight);
                    editText_breakfast = (EditText) findViewById(R.id.breakfast);
                    editText_lunch = (EditText) findViewById(R.id.lunch);
                    editText_dinner = (EditText) findViewById(R.id.dinner);
                    editText_snack = (EditText) findViewById(R.id.snack);

                    editText_breakfastCalories = (EditText) findViewById(R.id.breakfastCalories);
                    editText_lunchCalories = (EditText) findViewById(R.id.lunchCalories);
                    editText_dinnerCalories = (EditText) findViewById(R.id.dinnerCalories);
                    editText_snackCalories = (EditText) findViewById(R.id.snackCalories);

                    editText_weight.setText((String) dateInfo.get(0));
                    initWeightEmpty = editText_weight.getText().toString().equals("");

                    editText_breakfast.setText((String) dateInfo.get(1));
                    editText_breakfastCalories.setText((String) dateInfo.get(2));

                    editText_lunch.setText((String) dateInfo.get(3));
                    editText_lunchCalories.setText((String) dateInfo.get(4));

                    editText_dinner.setText((String) dateInfo.get(5));
                    editText_dinnerCalories.setText((String) dateInfo.get(6));

                    editText_snack.setText((String) dateInfo.get(7));
                    editText_snackCalories.setText((String) dateInfo.get(8));
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
        DatabaseReference myref_my = database.getReference(monthyear);
        myref_my.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String s = dataSnapshot.getValue(String.class);
                try {
                    if (s == null) {
                        int[] arr = {0, 0};
                        avgWeight = new JSONArray(arr);
                    } else {
                        avgWeight = new JSONArray(s);
                    }
                } catch (JSONException e) {
                    Toast.makeText(FoodActivity.this, "Error3", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    protected void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_date = database.getReference(date);
        DatabaseReference myref_my = database.getReference(monthyear);

        editText_weight = (EditText) findViewById(R.id.weight);
        weight = editText_weight.getText().toString();
        if (initWeightEmpty && !weight.equals("")) {
            denom++;
        }
        if (!initWeightEmpty && weight.equals("")) {
            denom--;
        }
        try {
            int w = 0;
            if (!weight.equals("")) {
                w = Integer.parseInt(weight);
            }
            avg = (Integer.parseInt((String) avgWeight.get(0)) * Integer.parseInt((String) avgWeight.get(1)) + w)/ denom;
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error2", Toast.LENGTH_SHORT).show();
        }

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

        editText_snack = (EditText) findViewById(R.id.snack);
        snack = editText_snack.getText().toString();

        editText_snackCalories = (EditText) findViewById(R.id.snackCalories);
        snackCalories = editText_snackCalories.getText().toString();

        try {
            dateInfo.put(0, weight);
            dateInfo.put(1, breakfast);
            dateInfo.put(2, breakfastCalories);
            dateInfo.put(3, lunch);
            dateInfo.put(4, lunchCalories);
            dateInfo.put(5, dinner);
            dateInfo.put(6, dinnerCalories);
            dateInfo.put(7, snack);
            dateInfo.put(8, snackCalories);
            avgWeight.put(0, avg);
            avgWeight.put(1, denom);
            myref_date.setValue(dateInfo.toString());
            myref_my.setValue(avgWeight.toString());
            Toast.makeText(foodView.getContext(),
                    "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CalendarActivity.class);
            startActivity(i);
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error2", Toast.LENGTH_SHORT).show();
        }
    }

}