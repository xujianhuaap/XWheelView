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
    public float dividerY;
    public int upRectColor;
    public int downRectColor;

    public ItemView(Context context) {
        super(context,null,0,R.style.WheelViewItemTheme);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        //绘制
        
        paint.setColor(upRectColor);
        Rect topRect=new Rect();
        canvas.drawRect(topRect,paint);

        paint.setColor(downRectColor);

    }
}
