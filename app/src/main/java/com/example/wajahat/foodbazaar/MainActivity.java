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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private List<Word> words= new ArrayList<>();
private RecyclerView recyclerView;
private  LeftMasterAdpater leftMasterAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.left_frame_recycler_view);
        leftMasterAdpater=new LeftMasterAdpater(this, words);
        recyclerView.setAdapter(leftMasterAdpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        leftMasterAdpater.notifyDataSetChanged();
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
