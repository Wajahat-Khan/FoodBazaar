package com.example.wajahat.foodbazaar;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wajahat.foodbazaar.Adapters.StartCategoriesAdapter;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.ViewModels.ItemsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    private List<Items> items=new ArrayList<>();
private List<Items> ordered_items=new ArrayList<>();
private RecyclerView leftRecyclerView;
private RecyclerView rightRecylcerView;
private  LeftMasterAdpater leftMasterAdpater;
private RightListAdapter rightListAdapter;
*/
private ItemsViewModel itemsViewModel;
private List<Categories> allCategories=new ArrayList<>();
/*
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
   */
private  StartCategoriesAdapter startCategoriesAdapter;
private  RecyclerView categoriesrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesrecyclerView=findViewById(R.id.all_categories);
        startCategoriesAdapter=new StartCategoriesAdapter(this, new StartCategoriesAdapter.AdapterListener() {
            @Override
            public void onClick(View view, int position, List<Categories> cat) {
                allCategories=cat;
                Categories temp=allCategories.get(position);
                //Snackbar.make(view, temp.getSub_categories() + " items", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                String subCats=temp.getSub_categories();
                String subCategories[]=subCats.split(",");
                Intent intent=new Intent(getBaseContext(),SecondActivity.class);
                intent.putExtra("subCategories",subCategories);
                startActivity(intent);
            }

            @Override
            public void onClick(View catView, int adapterPosition) {

            }
        });
        categoriesrecyclerView.setAdapter(startCategoriesAdapter);
        categoriesrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesrecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
        rightRecylcerView=findViewById(R.id.right_frame_recycler_view);
        leftRecyclerView=findViewById(R.id.left_frame_recycler_view);

        final RightListAdapter rightListAdapter  = new RightListAdapter(this, new RightListAdapter.MyAdapterListener() {
            @Override
            public void onClick(View view, int position, List<Items> it) {
               items=it;
               Items temp=items.get(position);
               ordered_items.add(temp);
                Snackbar.make(view, temp.getName() + "ordered", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
                rightRecylcerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rightRecylcerView.setAdapter(rightListAdapter);
        rightRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        final LeftMasterAdpater leftMasterAdpater  = new LeftMasterAdpater(this);
        leftRecyclerView.setAdapter(leftMasterAdpater);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));

*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel.class);
/*
        itemsViewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(@Nullable List<Items> items) {
                rightListAdapter.setItems(items);
                items=rightListAdapter.getItems();

            }
        });
*/
        itemsViewModel.getAllCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(@Nullable List<Categories> categories) {
                startCategoriesAdapter.setCategories(categories);

            }
        });

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
