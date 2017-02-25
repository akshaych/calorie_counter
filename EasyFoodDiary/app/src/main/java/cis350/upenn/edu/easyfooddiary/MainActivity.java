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
        else if (view.getId() == R.id.calendar) {
            Intent i = new Intent(this, CalendarActivity.class);
            startActivity(i);
        }
    }
}
