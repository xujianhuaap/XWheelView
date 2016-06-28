package com.xujianhuaap.xwheelview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private XWheelView wheelView;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wheelView = (XWheelView) findViewById(R.id.wheel_view);
        TextView tv1=new ItemView(MainActivity.this);
        tv1.setText("111111111");
        TextView tv2=new ItemView(MainActivity.this);
        tv2.setText("222222222");
        TextView tv3=new ItemView(MainActivity.this);
        tv3.setText("333333333");
        density =getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(100*density));
        wheelView.addView(tv1,layoutParams);
        wheelView.addView(tv2,layoutParams);
        wheelView.addView(tv3,layoutParams);

        startActivity(new Intent(this,FirstActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        wheelView.setBackgroundColor(Color.parseColor("#0000ff00"));
    }
}
