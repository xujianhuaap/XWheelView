package com.xujianhuaap.xwheelview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xujianhuaap.xwheelview.util.LogUtil;

public class FirstActivity extends AppCompatActivity {

    private TextView btn1, btn2;
    private String TAG=FirstActivity.class.getName();
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seond);
        btn1 =(TextView)findViewById(R.id.btn_1);
        btn2 =(TextView)findViewById(R.id.btn_2);

        LogUtil.d(TAG,FirstActivity.this.hashCode()+"");
    }

    public void onBtnClick(View v){
        if(v==btn1){
            Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
            startActivity(intent);
        }else if(v==btn2){
            Intent intent=new Intent(FirstActivity.this,ThirdActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            finish();
        }

    }

}
