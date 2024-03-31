package com.example.a4200_group_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //   TEST - detect swipe   ---------------------------------------------------

    //    private GestureDetector gestureDetector = new GestureDetector(MainActivity.this, this);
    private float x0;
    private float y0;
    private final float swipe_threahold = 200;

    private float x0;
    private float y0;
    private final float swipe_threahold = 200;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        gestureDetector.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x0 = event.getX(); // when finger down, save this as start point
            y0 = event.getY();
            System.out.println("finger down");

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float x_dist = event.getX() - x0; // when finger up, calculate distance
            float y_dist = event.getY() - y0;
            System.out.println("finger up: x_dist = " + x_dist + ", y_dist =" + y_dist);

            if (Math.abs(x_dist) < Math.abs(y_dist)) {
                System.out.println("-> this is a vertical swipe");
                return false;
            }

            if (Math.abs(x_dist) > swipe_threahold) {
                OnSwipeX(x_dist);

                return true; // this input in handled, so other UI won't react to it
            }
        }

        return false;
    }

    private void OnSwipeX(float x_dist)
    {
        if (x_dist > 0) {
            System.out.println("-> swip right");
        } else {
            System.out.println("-> swip left");
        }
    }


}
