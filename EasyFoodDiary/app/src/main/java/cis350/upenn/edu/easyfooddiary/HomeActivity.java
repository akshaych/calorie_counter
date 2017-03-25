package cis350.upenn.edu.easyfooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View view) {
        //have to differentiate amongst buttons
        if (view.getId() == R.id.login) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.sign) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
