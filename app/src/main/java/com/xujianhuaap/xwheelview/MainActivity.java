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
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private XWheelView wheelView;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wheelView = (XWheelView) findViewById(R.id.wheel_view);
        TextView tv0=new ItemView(MainActivity.this,0);
        tv0.setText("0000000000");
        TextView tv1=new ItemView(MainActivity.this,0);
        tv1.setText("111111111");
        TextView tv2=new ItemView(MainActivity.this,1);
        tv2.setText("222222222");
        TextView tv3=new ItemView(MainActivity.this,2);
        tv3.setText("333333333");
        TextView tv4=new ItemView(MainActivity.this,2);
        tv4.setText("44444444444");
        TextView tv5=new ItemView(MainActivity.this,2);
        tv5.setText("5555555555555555");
        density =getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(100*density));
        layoutParams.topMargin=-(int)(100*density);
        tv0.setLayoutParams(layoutParams);
        wheelView.addView(tv0,0,layoutParams);
        layoutParams.topMargin=-0;
        wheelView.addView(tv1,1,layoutParams);
        wheelView.addView(tv2,2,layoutParams);
        wheelView.addView(tv3,3,layoutParams);
        wheelView.addView(tv4,4,layoutParams);
        wheelView.addView(tv5,5,layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wheelView.setBackgroundColor(Color.parseColor("#0000ff00"));
    }
}
