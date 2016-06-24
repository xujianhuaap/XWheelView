package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xujianhuaap.xwheelview.util.LogUtil;

/**
 * Created by xujianhua on 2016/6/23.
 */
public class XWheelView extends LinearLayout{

    public final static String TAG=XWheelView.class.getName();
    public  int FIRST_LINE_Y=0;
    public  int SECOND_LINE_Y=0;
    public  int THIRD_LINE_Y=0;
    private int backGroundColor;

    public XWheelView(Context context) {
        super(context);
    }

    public XWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.wheel_view,0,0);
        float dividerWidth=typedArray.getInteger(R.styleable.wheel_view_divider_width,0);
        backGroundColor = typedArray.getColor(R.styleable.wheel_view_background_color,0);
        LogUtil.d(TAG,"dividerWidth \t"+dividerWidth);
    }

    public XWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(R.styleable.wheel_view);
        float dividerWidth=typedArray.getInteger(R.styleable.wheel_view_divider_width,0);
        LogUtil.d(TAG,"dividerWidth"+dividerWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        FIRST_LINE_Y=100;
        SECOND_LINE_Y=200;
        THIRD_LINE_Y=300;
        //三条分割线
        canvas.drawLine(0,FIRST_LINE_Y,300,FIRST_LINE_Y,paint);
        canvas.drawLine(0,SECOND_LINE_Y,300,SECOND_LINE_Y,paint);
        canvas.drawLine(0,THIRD_LINE_Y,300,THIRD_LINE_Y,paint);
//        画弧线
//        int l=THIRD_LINE_Y-SECOND_LINE_Y;
//        double radius=l/Math.PI;
//        int xDot=100;
//        int yDot=SECOND_LINE_Y;
//
//        paint.setColor(Color.GREEN);
        //绘制背景
        canvas.drawColor(backGroundColor);
        LogUtil.d(TAG,"-------onDraw-----");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.d(TAG,"-------onMeasure-----");
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LogUtil.d(TAG,"-------onScrollChanged-----"+l);
    }


}
