package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Daniel on 3/24/2017.
 */
    public class VitalsActivity extends AppCompatActivity {

    protected VitalsView vitalsView;

    protected String heartRate;
    protected String bloodPressureS;
    protected String bloodPressureD;
    protected String bodyTemperature;
    protected String respiratoryRate;

    protected EditText editText_heartRate;
    protected EditText editText_bloodPressureS;
    protected EditText editText_bloodPressureD;
    protected EditText editText_bodyTemperature;
    protected EditText editText_respiratoryRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
        vitalsView = new VitalsView(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}

