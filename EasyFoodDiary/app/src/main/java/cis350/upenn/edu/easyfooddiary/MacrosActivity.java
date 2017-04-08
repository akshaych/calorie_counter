package cis350.upenn.edu.easyfooddiary;

/**
 * Created by Daniel on 4/7/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by haile on 4/7/2017.
 */

public class MacrosActivity extends AppCompatActivity {

    protected EditText editText_calories;
    protected TextView title, carbs, protein, fat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymacros);
        title = (TextView) findViewById(R.id.title);
        title.setText("Amount of Macros Needed to Achieve Goals!");
        carbs = (TextView) findViewById(R.id.carbs);
        protein = (TextView) findViewById(R.id.protein);
        fat = (TextView) findViewById(R.id.fat);
    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        /*else if (view.getId() == R.id.calculate) {;
            String calories = editText_calories.getText().toString();
            if (calories.equals("")) {
                Toast.makeText(MacrosActivity.this, "Enter calories to burn", Toast.LENGTH_SHORT).show();
            }
            else {
                int cal = Integer.parseInt(calories);
                pushups.setText("Pushups needed: " + cal * 5);
                running.setText("Miles to run: " + cal / 100.0);
                biking.setText("Miles to bike: " + cal / 30.0);
                walking.setText("Miles to walk: " + cal / 50.0);
            }
        }*/
    }
}
