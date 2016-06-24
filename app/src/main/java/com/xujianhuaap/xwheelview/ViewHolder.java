package com.xujianhuaap.xwheelview;

import android.content.Context;
import android.view.TextureView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by xujianhua on 2016/6/24.
 */
public class ViewHolder {
    ArrayList<View>  views=new ArrayList<>();
    private Context context;
    public View getView(int index) {
        View view=views.get(index);
        if(view==null){
            view=new ItemView(context);
            views.add(index,view);
        }
        return view;
    }

    public void resetView(){

    }
}
