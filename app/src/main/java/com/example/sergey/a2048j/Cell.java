package com.example.sergey.a2048j;


import android.content.Context;
import android.graphics.Color;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Sergey on 13.06.2017.
 */

public class Cell extends LinearLayout {
    private TextView view;

    public Cell(Context context) {
        super(context);
        view = new TextView(context);
        view.setTextColor(Color.WHITE);
        this.setGravity(Gravity.CENTER);
        this.addView(view);
    }

    public void setText(String text) {
        view.setText(text);
    }
}