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
    private WheelViewAdapter<String> adapter;

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
        STANDARD_DIMEN=(1*viewHeight)/3;
        FIRST_LINE_Y=STANDARD_DIMEN;
        SECOND_LINE_Y=STANDARD_DIMEN*2;
        THIRD_LINE_Y=STANDARD_DIMEN*3;
        //三条分割线
        canvas.drawLine(0,FIRST_LINE_Y,viewWidth,FIRST_LINE_Y,paint);
        canvas.drawLine(0,SECOND_LINE_Y,viewWidth,SECOND_LINE_Y,paint);

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
                if(i==0){
                    LinearLayout.LayoutParams layoutParams=(LayoutParams) itemView.getLayoutParams();
                    layoutParams.topMargin=-STANDARD_DIMEN;
                    itemView.setLayoutParams(layoutParams);
                    requestLayout();
                }
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
                if(Math.abs(totalLen-lasteTotalLen)>0){
                    if(totalLen-lasteTotalLen>0){
                        isDown=true;
                    }else {
                        isDown=false;
                    }
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
        boolean isNext=false;
        position = (int)(totalLen/STANDARD_DIMEN);
        if(Math.abs(position-lastPosition)==1){
            position=lastPosition;
            isNext=true;
        }
//        int dataCount=adapter.getCount();
//        int index=position/dataCount;
        for(int i=0;i<itemViews.size();i++){
            ItemView itemView=itemViews.get(i);
            //滚动
            if(itemViews.size()-1==i){
                itemView.setTranslationY((dividerY-4*STANDARD_DIMEN));
            }else {
                itemView.setTranslationY((dividerY));
            }
            //背景颜色
            if(isNext){
                itemView.restoreViewContent((4-position%4-1+i)%4);
                refreshView(itemView, (4-position%4-1+i)%4,dividerY);
            }else {
                itemView.restoreViewContent((4-position%4+i)%4);
                refreshView(itemView,(4-position%4+i)%4,dividerY);
            }
            LogUtil.d(TAG,"-------scroll i-----"+i);
        }

    }

    /***
     *
     * @param itemView
     * @param index
     * @param dividerY
     */
    public void refreshView(ItemView itemView,int index,int dividerY){
        if(index%2==0){
            itemView.restoreView(FIRST_RECT_COLOR,FIRST_RECT_COLOR,dividerY);
        }else {
            itemView.restoreView(SECOND_RECT_COLOR,SECOND_RECT_COLOR,dividerY);
        }
    }

}
