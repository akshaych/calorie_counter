package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by haile on 3/24/2017.
 */

public class TrackerActivity extends AppCompatActivity {

    protected int m = 1;
    protected int[] weights = new int[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        while (m < 13) {
            DatabaseReference myref_year = database.getReference(m + ",2017");
            myref_year.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String s = dataSnapshot.getValue(String.class);
                    try {
                        if (s == null) {
                            weights[m - 1] = -1;
                        } else {
                            weights[m - 1] = Integer.parseInt((String) (new JSONArray(s)).get(0));
                        }
                    } catch (JSONException e) {
                        Toast.makeText(TrackerActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("tag", "Failed to read value.", error.toException());
                }
            });
        }
        DataPoint[] arr = new DataPoint[12];
        for (int i = 0; i < 12; i++) {
            if (weights[i] >= 0) {
                arr[i] = new DataPoint(i, weights [i]);
            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(arr);
        graph.addSeries(series);
    }
    public void onClick(View view) {
        if (view.getId() == R.id.main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
