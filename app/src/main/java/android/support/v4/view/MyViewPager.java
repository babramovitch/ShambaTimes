package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;

public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attr) {
        super(context, attr);
    }

    void smoothScrollTo(int x, int y, int velocity) {
        super.smoothScrollTo(x, y, 15);
    }
} 