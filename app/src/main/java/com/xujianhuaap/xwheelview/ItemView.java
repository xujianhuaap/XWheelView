package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xujianhuaap.xwheelview.util.LogUtil;

/**
 * Created by xujianhua on 2016/6/24.
 */
public class ItemView extends TextView{
    public int dividerY;
    public int upRectColor;
    public int downRectColor;
    private int viewTop;
    private int viewRight;
    private int viewBottom;
    private int viewLeft;
    private int viewHeight;

    private String TAG=ItemView.class.getName();

    public ItemView(Context context) {
        super(context,null,0,R.style.WheelViewItemTheme);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            viewTop=top;
            viewLeft=left;
            viewRight=right;
            viewBottom=bottom;
            viewHeight=viewBottom-viewTop;
            LogUtil.d(TAG,"onLayout viewTop"+viewTop);
            LogUtil.d(TAG,"onLayout viewBottom"+viewBottom);
        }
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        //绘制
        paint.setColor(upRectColor);
        int lineY=viewTop+viewHeight-dividerY;
        Rect topRect=new Rect(viewLeft,viewTop,viewRight,lineY);
        canvas.drawRect(topRect,paint);

        paint.reset();
        paint.setColor(downRectColor);
        Rect bottomRect=new Rect(viewLeft,lineY,viewRight,viewBottom);
        canvas.drawRect(bottomRect,paint);
        LogUtil.d(TAG,"onDraw dividerY \t"+dividerY+"lineY\t"+(lineY));

    }

    public void restoreView(int upRectColor,int downRectColor,int dividerY){
        this.upRectColor=upRectColor;
        this.downRectColor=downRectColor;
        this.dividerY=dividerY;
        invalidate();
    }
}
