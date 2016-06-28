package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.xujianhuaap.xwheelview.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by xujianhua on 2016/6/23.
 */
public class XWheelView extends LinearLayout{

    public final static String TAG=XWheelView.class.getName();
    public  int FIRST_LINE_Y=0;
    public  int SECOND_LINE_Y=0;
    public  int THIRD_LINE_Y=0;
    public int STANDARD_DIMEN=0;
    public int FIRST_RECT_COLOR;
    public int SECOND_RECT_COLOR;
    public int THIRD_RECT_COLOR;
    public int FOURTH_RECT_COLOR;
    private int backGroundColor;
    private float totalLen;
    private float initY=0;
    private float density=0;
    private int viewWidth;
    private int viewHeight;

    public XWheelView(Context context) {
        super(context);
        init(context,null);
    }

    public XWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public XWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }
    private void init(Context context,AttributeSet attrs) {
        if(attrs!=null){
            TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.wheel_view);
            float dividerWidth=typedArray.getInteger(R.styleable.wheel_view_divider_width,0);
            backGroundColor=typedArray.getColor(R.styleable.wheel_view_background_color,0);
            LogUtil.d(TAG,"dividerWidth"+dividerWidth);
        }
        FIRST_RECT_COLOR=context.getResources().getColor(R.color.color_first_rect);
        SECOND_RECT_COLOR=context.getResources().getColor(R.color.color_second_rect);
        THIRD_RECT_COLOR=context.getResources().getColor(R.color.color_third_rect);
        FOURTH_RECT_COLOR=context.getResources().getColor(R.color.color_fourth_rect);
        density=context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        STANDARD_DIMEN=(1*viewHeight)/4;
        FIRST_LINE_Y=STANDARD_DIMEN;
        SECOND_LINE_Y=STANDARD_DIMEN*2;
        THIRD_LINE_Y=STANDARD_DIMEN*3;
        //三条分割线
        canvas.drawLine(0,FIRST_LINE_Y,600,FIRST_LINE_Y,paint);
        canvas.drawLine(0,SECOND_LINE_Y,600,SECOND_LINE_Y,paint);
        canvas.drawLine(0,THIRD_LINE_Y,600,THIRD_LINE_Y,paint);
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
        //blank

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.d(TAG,"-------onMeasure-----");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LogUtil.d(TAG,"-------onLayout isChange-----"+changed);
        if(changed){
            viewWidth=r-l;
            viewHeight=b-t;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LogUtil.d(TAG,"-------onScrollChanged-----"+l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG,"-------onTouchEvent move-----");
                totalLen+=event.getY()-initY;
                initY=event.getY();
                scroll();
                return true;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG,"-------onTouchEvent up-----");
                break;
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG,"-------onTouchEvent down-----");
                initY=event.getY();
                return true;
            default:
                break;
        }
        return false;
    }
    public void scroll(){
        int dividerY=(int)(totalLen%STANDARD_DIMEN);
        int position=(int)(totalLen/STANDARD_DIMEN);
        for(int i=0;i<getChildCount();i++){
            View view=getChildAt(i);
            ItemView itemView=(ItemView)view;
            itemView.setTranslationY((totalLen));

            if(position+i==0){
                itemView.restoreView(FIRST_RECT_COLOR,SECOND_RECT_COLOR,dividerY);
            }else if(position+i==1){
                itemView.restoreView(SECOND_RECT_COLOR,THIRD_RECT_COLOR,dividerY);
            }else if(position+i==2){
                itemView.restoreView(THIRD_RECT_COLOR,FOURTH_RECT_COLOR,dividerY);
            }else if(position+i==3){
                itemView.restoreView(FOURTH_RECT_COLOR,FOURTH_RECT_COLOR,dividerY);
            }

        }
    }

    /***
     *
     * @param position
     */
    public void refreshView(int position){
        if(position==0){

        }else  if(position==1){

        }else  if(position==2){

        }else if(position==3){

        }
    }


}
