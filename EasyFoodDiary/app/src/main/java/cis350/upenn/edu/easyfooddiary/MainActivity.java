package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.your_information) {
            Intent i = new Intent(this, InformationActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.motivation) {
            Intent i = new Intent(this, MotivationActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.calendar) {
            Intent i = new Intent(this, CalendarActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.fitness_goals) {
            Intent i = new Intent(this, FitnessActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.nutrition) {
            Intent i = new Intent(this, NutritionActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.calc) {
            Intent i = new Intent(this, CalculatorActivity.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.bmi) {
            Intent i = new Intent(this, BMIActivity.class);
            startActivity(i);
        }
    }
}
