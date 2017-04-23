package com.recyclerdemo.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.Toast;

import com.recyclerdemo.R;
import com.recyclerdemo.controller.customAdapter;
import com.recyclerdemo.model.ListItem;
import com.recyclerdemo.model.listData;

import java.util.ArrayList;
import android.view.MenuInflater;
public class MainActivity extends AppCompatActivity implements customAdapter.ItemClickCallback{

    private RecyclerView Rec;
    private customAdapter myAdapter;
    private ArrayList listContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Rec = (RecyclerView)findViewById(R.id.recView);
        Rec.setLayoutManager(new GridLayoutManager(this,2));

        listContent = (ArrayList) com.recyclerdemo.model.listData.getListData();

        myAdapter = new customAdapter(listData.getListData(),this);
        myAdapter.setItemClickCallback(this);
        Rec.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public void onImageClick(int p) {
        ListItem a = (ListItem) listContent.get(p);
        Toast.makeText(this,"Image clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int p) {

        ListItem b = (ListItem) listContent.get(p);
        Toast.makeText(this,"Item clicked",Toast.LENGTH_SHORT).show();
    }


}
