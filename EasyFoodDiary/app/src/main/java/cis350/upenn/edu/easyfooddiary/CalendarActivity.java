package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 * Created by haile on 2/24/2017.
 */

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView view = new CalendarView(this);
        long date = Calendar.getInstance().getTimeInMillis();
        view.setDate(date);
        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent i = new Intent(CalendarActivity.this, FoodActivity.class);
                i.putExtra("DATE", view.getDate());
                startActivity(i);
            }
        });
    }
}
