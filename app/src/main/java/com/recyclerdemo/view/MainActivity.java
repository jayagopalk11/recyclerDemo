package com.recyclerdemo.view;


import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filterable;
import android.widget.Toast;

import com.recyclerdemo.R;
import com.recyclerdemo.controller.customAdapter;
import com.recyclerdemo.model.ListItem;
import com.recyclerdemo.model.listData;

import java.util.ArrayList;
import java.util.List;

import android.view.MenuInflater;
public class MainActivity extends AppCompatActivity implements customAdapter.ItemClickCallback , Filterable ,playerFragment.FragClickListener{

    private RecyclerView Rec;
    private customAdapter myAdapter;
    private ArrayList listContent;
    SearchView sv;
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
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        sv = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        sv.setIconifiedByDefault(true);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if ( TextUtils.isEmpty ( newText ) ) {
                    getFilter().filter("");
                } else {
                    getFilter().filter(newText);
                }
                return true;
            }
        });

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

    public android.widget.Filter getFilter() {
        return new android.widget.Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.i("FILTER_STRING",charSequence.toString());
                final List<ListItem> results = new ArrayList<ListItem>();
                final FilterResults oReturn = new FilterResults();
                for (final ListItem a : listData.getListData()){
                    if(a.getTitle().toLowerCase().contains(charSequence.toString())){
                        results.add(a);
                    }
                }
                oReturn.values = results;
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                myAdapter.data = (List<ListItem>)filterResults.values;
                myAdapter.notifyDataSetChanged();

            }
        };
    }


    @Override
    public void playerFrag(View view) {
        Toast.makeText(this,"Fragment clicked",Toast.LENGTH_SHORT).show();
    }
}
