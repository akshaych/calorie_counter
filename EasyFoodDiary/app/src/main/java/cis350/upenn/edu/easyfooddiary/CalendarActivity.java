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
        CalendarView view = (CalendarView) findViewById(R.id.calendar);
        long date = Calendar.getInstance().getTimeInMillis();
        view.setDate(date);
        String type = getIntent().getExtras().getString("Type");
        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if (type.equals("calendar")) {
                    Intent i = new Intent(CalendarActivity.this, FoodActivity.class);
                    int month_add;
                    month_add = month + 1;
                    i.putExtra("DATE", month_add + "," + dayOfMonth + "," + year);
                    i.putExtra("MONTHYEAR", month_add + "," + year);
                    view.getContext().startActivity(i);
                } else if (type.equals("vitals")) {
                    Intent i = new Intent(CalendarActivity.this, VitalsActivity.class);
                    int month_add;
                    month_add = month + 1;
                    i.putExtra("DATE", month_add + "," + dayOfMonth + "," + year);
                    i.putExtra("MONTHYEAR", month_add + "," + year);
                    view.getContext().startActivity(i);
                } else {
                    Intent i = new Intent(CalendarActivity.this, SleepActivity.class);
                    int month_add;
                    month_add = month + 1;
                    i.putExtra("DATE", month_add + "," + dayOfMonth + "," + year);
                    i.putExtra("MONTHYEAR", month_add + "," + year);
                    view.getContext().startActivity(i);
                }
            }
        });
    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

}
