package com.xujianhuaap.xwheelview.tool;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by xujianhua on 2016/6/23.
 */
public class PaintTool extends Paint{
    private static PaintTool paint;
    public static Paint getInstance(){
        if(paint==null){
            paint=new PaintBuilder().build();
        }
        return paint;
    }

    private PaintTool() {
    }


    public static class PaintBuilder{
        private PaintBuilder paintBuilder;
        private PaintTool paintTool;

        private static final int DEFAULT_COLOR=Color.parseColor("#ffffffff");

        private int color;
        private int width;

        public void setColor(int color){
            this.color=color;
        }

        public PaintTool build(){
            paintTool=new PaintTool();
            if(color==0){
                color=DEFAULT_COLOR;
            }
            paint.setColor(color);
            return paintTool;
        }
    }


}
