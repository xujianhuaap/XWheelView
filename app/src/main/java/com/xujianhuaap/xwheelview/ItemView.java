package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xujianhua on 2016/6/24.
 */
public class ItemView extends TextView{
    public ItemView(Context context) {
        super(context,null,0,R.style.WheelViewItemTheme);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
