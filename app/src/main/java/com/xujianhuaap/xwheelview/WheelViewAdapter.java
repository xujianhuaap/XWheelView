package com.xujianhuaap.xwheelview;

import java.util.ArrayList;

/**
 * Created by xujianhua on 2016/7/5.
 */
public class WheelViewAdapter <T>{
    public ArrayList<T> datas=new ArrayList<>();

    public int getPosition(){
        return 0;
    }

    public int getCount(){
        return datas.size();
    }

    public ItemView getView(){
        return null;
    }

}
