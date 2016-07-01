package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xujianhuaap.xwheelview.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private float lasteTotalLen;
    private float initY=0;
    private float density=0;
    private int viewWidth;
    private int viewHeight;
    private Context context;
    private boolean isDown;

    private List<ItemView> itemViews=new ArrayList<>();
    private int position;//
    private int lastPosition;

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
        this.context=context;
        if(attrs!=null){
            TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.wheel_view);
            float dividerWidth=typedArray.getInteger(R.styleable.wheel_view_divider_width,0);
            backGroundColor=typedArray.getColor(R.styleable.wheel_view_background_color,0);
            LogUtil.d(TAG,"dividerWidth"+dividerWidth);
            typedArray.recycle();//必不可少
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
        canvas.drawLine(0,FIRST_LINE_Y,viewWidth,FIRST_LINE_Y,paint);
        canvas.drawLine(0,SECOND_LINE_Y,viewWidth,SECOND_LINE_Y,paint);
        canvas.drawLine(0,THIRD_LINE_Y,viewWidth,THIRD_LINE_Y,paint);
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
            itemViews.clear();
            for(int i=0;i<getChildCount();i++){
                View view=getChildAt(i);
                ItemView itemView=(ItemView)view;
                itemViews.add(i,itemView);
            }

            LogUtil.d(TAG,"onLayout viewTop"+TimeUnit.SECONDS.toMillis(30));
            LogUtil.d(TAG,"onLayout viewBottom"+b);
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
                if(Math.abs(totalLen-lasteTotalLen)>60){
                    lasteTotalLen=totalLen;
                    scroll();
                }
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
        position = (int)(totalLen/STANDARD_DIMEN);
        for(int i=0;i<itemViews.size();i++){
            ItemView itemView=itemViews.get(i);
            itemView.setTranslationY((totalLen));
            refreshView(itemView, position +i,dividerY);
            LogUtil.d(TAG,"-------scroll i-----"+i);
        }
        if(position-lastPosition==1){
            ItemView tv0=new ItemView(context,0);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(100*density));
            tv0.setText("xxxxx");
            removeItemViewFormQueue(itemViews.size()-1);
            itemViews.remove(itemViews.size()-1);
            addQueue(tv0,layoutParams,0);
            itemViews.add(0,tv0);
            tv0.setTranslationY(totalLen-STANDARD_DIMEN);
            lastPosition=position;
        } else if(position-lastPosition==-1){
            ItemView tv0=new ItemView(context,0);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(100*density));
//            layoutParams.bottomMargin=(int)totalLen;
            tv0.setText("yyyyyy");
            removeItemViewFormQueue(0);
            itemViews.remove(0);
            addQueue(tv0,layoutParams,itemViews.size());
            itemViews.add(itemViews.size(),tv0);
            lastPosition=position;

        }
    }

    /***
     *
     * @param position
     */
    public void refreshView(ItemView itemView,int position,int dividerY){
        LogUtil.d(TAG,"refreshView");
        position=position%4;
        if(position==0){
            itemView.restoreView(FIRST_RECT_COLOR,SECOND_RECT_COLOR,dividerY);
        }else  if(position==1){
            itemView.restoreView(SECOND_RECT_COLOR,THIRD_RECT_COLOR,dividerY);
        }else  if(position==2){
            itemView.restoreView(THIRD_RECT_COLOR,FOURTH_RECT_COLOR,dividerY);
        }else if(position==3){
            itemView.restoreView(FOURTH_RECT_COLOR,FIRST_RECT_COLOR,dividerY);
        }else {
            itemView.restoreView(FOURTH_RECT_COLOR,Color.GREEN,dividerY);
        }
    }
    public void addQueue(TextView itemView,ViewGroup.LayoutParams layoutParams,int index){
        addView(itemView,index,layoutParams);
    }
    public void removeItemViewFormQueue(int index){
        removeViewAt(index);
    }


}
