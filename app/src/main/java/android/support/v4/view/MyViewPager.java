package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

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

    /*
    Getting a rare crash, catching the error here prevents the crash
    Fatal Exception: java.lang.IllegalArgumentException pointerIndex out of range
    https://code.google.com/p/android/issues/detail?id=64553
    */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
} 