package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daniel on 3/24/2017.
 */
    public class MonthCalendarActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_monthly_calendar);
            Calendar calendar = Calendar.getInstance();
            Date d1 = calendar.getTime();
            
            GraphView graph = (GraphView) findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3)
            });
            graph.addSeries(series);

            series.setTitle("Current Month Calories");
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

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

