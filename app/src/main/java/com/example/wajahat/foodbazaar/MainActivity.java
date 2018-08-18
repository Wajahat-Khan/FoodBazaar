package com.example.wajahat.foodbazaar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wajahat.foodbazaar.Adapters.LeftMasterAdpater;
import com.example.wajahat.foodbazaar.Adapters.RightListAdapter;
import com.example.wajahat.foodbazaar.Data.Items;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private List<Word> words= new ArrayList<>();
private List<Items> items=new ArrayList<>();
private RecyclerView leftRecyclerView;
private RecyclerView rightRecylcerView;
private  LeftMasterAdpater leftMasterAdpater;
private RightListAdapter rightListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leftRecyclerView=findViewById(R.id.left_frame_recycler_view);
        rightRecylcerView=findViewById(R.id.right_frame_recycler_view);

        leftMasterAdpater=new LeftMasterAdpater(this, words);
        rightListAdapter=new RightListAdapter(this, items);

        leftRecyclerView.setAdapter(leftMasterAdpater);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rightRecylcerView.setAdapter(rightListAdapter);
        rightRecylcerView.setLayoutManager(new LinearLayoutManager(this));
        PrepareData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }
    private void PrepareData(){
        Word word=new Word("Wajahat");
        words.add(word);
        word=new Word("Wajeeha");
        words.add(word);
        word=new Word("Ashar");
        words.add(word);
        word=new Word("Rana");
        words.add(word);
        word=new Word("Mana");
        words.add(word);
        word=new Word("ABC");
        words.add(word);
        word=new Word("ABC");
        words.add(word);
        word=new Word("ABC");
        words.add(word);

        Items item=new Items(1,"Chicken Burger", "Chicken burger very good.Chicken burger very good.Chicken burger very good.", 125 , null, true, "Chicken burger " +
                "very good. Chicken burger very good. Chicken burger very good. Chicken burger very good.", null,"chicken burgers","burgers",0,"chicken;bun");
         items.add(item);
         item= new
                 Items(2,"Chicken Zinger", "Chicken burger very good.Chicken burger very good.Chicken burger very good.", 125 , null, true, "Chicken burger " +
                 "very good. Chicken burger very good. Chicken burger very good. Chicken burger very good.", null,"chicken burgers","burgers",0,"chicken;bun");
         items.add(item);
         item=new
                 Items(3,"Chicken Cheese Burger", "Chicken burger very good.Chicken burger very good.Chicken burger very good.", 125 , null, true, "Chicken burger " +
                 "very good. Chicken burger very good. Chicken burger very good. Chicken burger very good.", null,"chicken burgers","burgers",0,"chicken;bun");

        leftMasterAdpater.notifyDataSetChanged();
        rightListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
