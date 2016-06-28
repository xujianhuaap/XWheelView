package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

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
            viewBottom=right;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        //绘制
        
        paint.setColor(upRectColor);
        Rect topRect=new Rect(viewLeft,viewTop,viewRight,dividerY);
        canvas.drawRect(topRect,paint);

        paint.setColor(downRectColor);
        Rect bottomRect=new Rect(viewLeft,dividerY,viewRight,viewBottom);
        canvas.drawRect(bottomRect,paint);

    }

    public void restoreView(int upRectColor,int downRectColor,int dividerY){
        this.upRectColor=upRectColor;
        this.downRectColor=downRectColor;
        this.dividerY=dividerY;
        invalidate();
    }
}
