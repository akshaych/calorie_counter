package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Daniel on 3/24/2017.
 */
    public class VitalsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vitals);
        }

        public void onClick(View view) {
            if (view.getId() == R.id.main) {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }
    }

