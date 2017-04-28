package cis350.upenn.edu.easyfooddiary;

/**
 * Created by vamsee on 4/27/17.
 */

public class SleepActivity {

    protected SleepView sleepView;
    protected String date;
    protected String monthyear;
    protected String bedtime, hours;
    protected JSONArray dateInfo;

    protected EditText editText_bedtime;
    protected EditText editText_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        sleepView = new sleepView(this);
        date = getIntent().getExtras().getString("DATE");
        monthyear = getIntent().getExtras().getString("MONTHYEAR");
        DatabaseReference myref_date = database.getReference(date + "v");
        myref_date.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                try {
                    if (s == null) {
                        String[] arr = {"", ""};
                        dateInfo = new JSONArray(arr);
                    } else {
                        dateInfo = new JSONArray(s);
                    }
                    editText_bedtime = (EditText) findViewById(R.id.bedtime);
                    editText_hours = (EditText) findViewById(R.id.hours);

                    editText_bedtime.setText((String) dateInfo.get(0));
                    editText_hours.setText((String) dateInfo.get(1));

                } catch (JSONException e) {
                    Toast.makeText(FoodActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                }

            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        }

    }

    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        editText_bedtime = (EditText) findViewById(R.id.bedtime);
        bedtime = editText_bedtime.getText().toString();

        editText_hours = (EditText) findViewById(R.id.hours);
        hours = editText_hours.getText().toString();

        try {
            dateInfo.put(0, bedtime);
            datInfo.put(1, hours);
        } catch (JSONException e) {
            Toast.makeText(FoodActivity.this, "Error2", Toast.LENGTH_SHORT).show();
        }

    }
}

