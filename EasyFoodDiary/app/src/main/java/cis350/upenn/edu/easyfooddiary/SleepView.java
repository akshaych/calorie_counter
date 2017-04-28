package cis350.upenn.edu.easyfooddiary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by vamsee on 4/27/17.
 */

public class SleepView extends TextView{

    protected String bedtime, hours;

    public SleepView(Context c) {
        super(c);
    }

    public SleepView(Context c, AttributeSet a) {
        super(c, a);
    }
}
