package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by akshay on 3/31/17.
 */

public class NutritionActivity extends AppCompatActivity {
    protected NutritionView nutritionView;
    protected JSONArray info;
    protected String calories, carbs, protein, fat;

    protected EditText editText_calories;
    protected EditText editText_carbs;
    protected EditText editText_protein;
    protected EditText editText_fat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        nutritionView = new NutritionView(this);
        final TextView myView = (TextView) findViewById(R.id.textView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref_nutrition = database.getReference("nutrition");
        myref_nutrition.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String s = dataSnapshot.getValue(String.class);
                try {
                    if (s == null) {
                        String[] arr = {"", "", "", ""};
                        info = new JSONArray(arr);
                    } else {
                        info = new JSONArray(s);
                    }
                    editText_calories = (EditText) findViewById(R.id.calories);
                    editText_carbs = (EditText) findViewById(R.id.carbs);
                    editText_protein = (EditText) findViewById(R.id.protein);
                    editText_fat = (EditText) findViewById(R.id.fat);

                    editText_calories.setText((String) info.get(0));

                    editText_carbs.setText((String) info.get(1));
                    editText_protein.setText((String) info.get(2));

                    editText_fat.setText((String) info.get(3));

                } catch (JSONException e) {
                    Toast.makeText(NutritionActivity.this, "Error1", Toast.LENGTH_SHORT).show();
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
        DatabaseReference myref_nutrition = database.getReference("nutrition");

        editText_calories = (EditText) findViewById(R.id.calories);
        calories = editText_calories.getText().toString();

        editText_carbs = (EditText) findViewById(R.id.carbs);
        carbs = editText_carbs.getText().toString();

        editText_protein = (EditText) findViewById(R.id.protein);
        protein = editText_protein.getText().toString();

        editText_fat = (EditText) findViewById(R.id.fat);
        fat = editText_fat.getText().toString();

        if (Integer.parseInt(carbs) + Integer.parseInt(protein) + Integer.parseInt(fat) == 100) {

            try {
                info.put(0, calories);
                info.put(1, carbs);
                info.put(2, protein);
                info.put(3, fat);
                myref_nutrition.setValue(info.toString());
                Toast.makeText(nutritionView.getContext(),
                        "Saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } catch (JSONException e) {
                Toast.makeText(NutritionActivity.this, "Error2", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(NutritionActivity.this, "Macros don't add to 100 %", Toast.LENGTH_SHORT).show();
        }
    }
}
