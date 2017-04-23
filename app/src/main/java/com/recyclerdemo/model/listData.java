package com.recyclerdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jai on 4/14/2017.
 */

public class listData {
    public static final String[] titles = {"mobile phone","head set","Laptop computer","mp3 player"};
    public static final int[] icons = {android.R.drawable.ic_menu_rotate,android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_compass,android.R.drawable.ic_menu_day};

    public static List<ListItem> getListData(){
        List<ListItem> data = new ArrayList<>();

        for(int i = 0 ; i < 4 ; i++){
            for (int j=0 ; j<icons.length ; j++){
                ListItem item = new ListItem();
                item.setTitle(titles[j]);
                item.setImageResId(icons[j]);
                data.add(item);
            }
        }
        return data;
    }

}
