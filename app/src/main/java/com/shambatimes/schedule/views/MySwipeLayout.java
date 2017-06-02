package com.shambatimes.schedule.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;

/**
 * Created by babramovitch on 5/31/2017.
 */

public class MySwipeLayout extends SwipeLayout {
    public MySwipeLayout(Context context) {
        this(context, null);
    }

    public MySwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * Process the surface release event.
     *
     * @param xvel                 xVelocity
     * @param yvel                 yVelocity
     * @param isCloseBeforeDragged the open state before drag
     */
    @Override
    protected void processHandRelease(float xvel, float yvel, boolean isCloseBeforeDragged) {
        float minVelocity = 0.1f;

        View surfaceView = getSurfaceView();
        DragEdge currentDragEdge = DragEdge.Left;
        if (currentDragEdge == null || surfaceView == null) {
            return;
        }
        float willOpenPercent = 0.5f;
        if (currentDragEdge == DragEdge.Left) {
            if (xvel > minVelocity) open();
            else if (xvel < -minVelocity) close();
            else {
                float openPercent = (1f * getSurfaceView().getLeft() / getDragDistance()) * -1;
                if (openPercent > willOpenPercent) open();
                else close();
            }
        }
    }
}

