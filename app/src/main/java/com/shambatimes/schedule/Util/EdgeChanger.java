package com.shambatimes.schedule.Util;

import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.EdgeEffect;
import android.widget.ScrollView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by babramovitch on 8/17/2015.
 * <p/>
 * Set the overscroll colors for the listviews, viewpagers and recyclerviews
 * <p/>
 * http://stackoverflow.com/questions/27342957/how-to-change-the-color-of-overscroll-edge-and-overscroll-glow/27343228#27343228
 * http://stackoverflow.com/questions/14940882/android-viewpager-edgeeffect-custom-color
 * http://stackoverflow.com/questions/28978989/set-recyclerview-edge-glow-pre-lollipop-when-using-appcompat
 */
public class EdgeChanger {

    private static final Class<?> CLASS_SCROLL_VIEW = ScrollView.class;
    private static final Field SCROLL_VIEW_FIELD_EDGE_GLOW_TOP;
    private static final Field SCROLL_VIEW_FIELD_EDGE_GLOW_BOTTOM;

    private static final Class<?> CLASS_LIST_VIEW = AbsListView.class;
    private static final Field LIST_VIEW_FIELD_EDGE_GLOW_TOP;
    private static final Field LIST_VIEW_FIELD_EDGE_GLOW_BOTTOM;

    static {
        Field edgeGlowTop = null, edgeGlowBottom = null;

        for (Field f : CLASS_SCROLL_VIEW.getDeclaredFields()) {
            switch (f.getName()) {
                case "mEdgeGlowTop":
                    f.setAccessible(true);
                    edgeGlowTop = f;
                    break;
                case "mEdgeGlowBottom":
                    f.setAccessible(true);
                    edgeGlowBottom = f;
                    break;
            }
        }

        SCROLL_VIEW_FIELD_EDGE_GLOW_TOP = edgeGlowTop;
        SCROLL_VIEW_FIELD_EDGE_GLOW_BOTTOM = edgeGlowBottom;

        for (Field f : CLASS_LIST_VIEW.getDeclaredFields()) {
            switch (f.getName()) {
                case "mEdgeGlowTop":
                    f.setAccessible(true);
                    edgeGlowTop = f;
                    break;
                case "mEdgeGlowBottom":
                    f.setAccessible(true);
                    edgeGlowBottom = f;
                    break;
            }
        }

        LIST_VIEW_FIELD_EDGE_GLOW_TOP = edgeGlowTop;
        LIST_VIEW_FIELD_EDGE_GLOW_BOTTOM = edgeGlowBottom;

    }

    public static void setEdgeGlowColor(ViewPager viewPager, int color) {
        try {
            Class<?> clazz = ViewPager.class;
            for (String name : new String[]{
                    "mLeftEdge", "mRightEdge"
            }) {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                Object edge = field.get(viewPager); // android.support.v4.widget.EdgeEffectCompat
                Field fEdgeEffect = edge.getClass().getDeclaredField("mEdgeEffect");
                fEdgeEffect.setAccessible(true);
                setEdgeEffectColor((EdgeEffect) fEdgeEffect.get(edge), color);
            }
        } catch (Exception ignored) {
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setEdgeGlowColor(AbsListView listView, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {

                EdgeEffect ee;
                ee = (EdgeEffect) LIST_VIEW_FIELD_EDGE_GLOW_TOP.get(listView);
                ee.setColor(color);
                ee = (EdgeEffect) LIST_VIEW_FIELD_EDGE_GLOW_BOTTOM.get(listView);
                ee.setColor(color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void setEdgeGlowColor(RecyclerView recyclerView, final int color) {
        try {
            final Class<?> clazz = RecyclerView.class;
            for (final String name : new String[]{"ensureTopGlow", "ensureBottomGlow", "ensureLeftGlow", "ensureRightGlow"}) {
                Method method = clazz.getDeclaredMethod(name);
                method.setAccessible(true);
                method.invoke(recyclerView);
            }
            for (final String name : new String[]{"mTopGlow", "mBottomGlow", "mRightGlow", "mLeftGlow"}) {
                final Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                final Object edge = field.get(recyclerView);
                final Field fEdgeEffect = edge.getClass().getDeclaredField("mEdgeEffect");
                fEdgeEffect.setAccessible(true);
                setEdgeEffectColor((EdgeEffect) fEdgeEffect.get(edge), color);
            }
        } catch (final Exception | NoClassDefFoundError ignored) {
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setEdgeGlowColor(ScrollView scrollView, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                EdgeEffect ee;
                ee = (EdgeEffect) SCROLL_VIEW_FIELD_EDGE_GLOW_TOP.get(scrollView);
                ee.setColor(color);
                ee = (EdgeEffect) SCROLL_VIEW_FIELD_EDGE_GLOW_BOTTOM.get(scrollView);
                ee.setColor(color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void setEdgeEffectColor(EdgeEffect edgeEffect, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                edgeEffect.setColor(color);
                return;
            }
            Field edgeField = EdgeEffect.class.getDeclaredField("mEdge");
            Field glowField = EdgeEffect.class.getDeclaredField("mGlow");
            edgeField.setAccessible(true);
            glowField.setAccessible(true);
            Drawable mEdge = (Drawable) edgeField.get(edgeEffect);
            Drawable mGlow = (Drawable) glowField.get(edgeEffect);
            mEdge.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mGlow.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mEdge.setCallback(null); // free up any references
            mGlow.setCallback(null); // free up any references
        } catch (Exception ignored) {
        }
    }

}
