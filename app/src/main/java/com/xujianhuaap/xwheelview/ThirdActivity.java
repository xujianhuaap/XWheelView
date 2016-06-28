package com.xujianhuaap.xwheelview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xujianhuaap.xwheelview.util.LogUtil;

public class ThirdActivity extends AppCompatActivity {

    private TextView btn1, btn2;
    private String TAG=ThirdActivity.class.getName();
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seond);
        btn1 =(TextView)findViewById(R.id.btn_1);
        btn2 =(TextView)findViewById(R.id.btn_2);
        btn1.setText("跳转到 FirstActivity");
        btn2.setText("跳转到 SecondActivity");

        LogUtil.d(TAG,ThirdActivity.this.hashCode()+"");
    }

    public void onBtnClick(View v){
        if(v==btn1){
            Intent intent=new Intent(ThirdActivity.this,FirstActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else if(v==btn2){
            Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else {
            finish();
        }

    }

}
