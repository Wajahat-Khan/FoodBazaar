package com.example.wajahat.foodbazaar;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wajahat.foodbazaar.Adapters.LeftMasterAdpater;
import com.example.wajahat.foodbazaar.Adapters.RightListAdapter;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.ViewModels.ItemsViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<Items> items=new ArrayList<>();

private RecyclerView leftRecyclerView;
private RecyclerView rightRecylcerView;
private LeftMasterAdpater leftMasterAdpater;
private RightListAdapter rightListAdapter;

    private ItemsViewModel itemsViewModel;
    private  HashMap<Integer,Integer> order_items=new HashMap<>();
    final int default_quantity=1;
    /*
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
       */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        rightRecylcerView=findViewById(R.id.right_frame_recycler_view);
        leftRecyclerView=findViewById(R.id.left_frame_recycler_view);

        rightListAdapter  = new RightListAdapter(this, new RightListAdapter.MyAdapterListener() {
            @Override
            public void onClick(View view, int position, List<Items> it) {
               items=it;
               Items temp=items.get(position);
               if(order_items.containsKey(temp.getId())) {
                    int curr=order_items.get(temp.getId());
                    curr++;
                    order_items.put(temp.getId(),curr);
               }
               else
               {order_items.put(temp.getId(),default_quantity);
               }
                Snackbar.make(view, temp.getName() + "ordered" + order_items.get(temp.getId())+ "times", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
                rightRecylcerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rightRecylcerView.setAdapter(rightListAdapter);
        rightRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        final LeftMasterAdpater leftMasterAdpater  = new LeftMasterAdpater(this);
        leftRecyclerView.setAdapter(leftMasterAdpater);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel.class);
        itemsViewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(@Nullable List<Items> items) {
                rightListAdapter.setItems(items);


            }
        });
        itemsViewModel.getAllCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(@Nullable List<Categories> cats) {
                String[] subCategories=getIntent().getStringArrayExtra("subCategories");
                leftMasterAdpater.setCategories(subCategories);

            }
        });

        findViewById(R.id.order_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SecondActivity.class);
                intent.putExtra("order_items", order_items);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("order_items",order_items);
        setResult(Activity.RESULT_OK,intent);
        finish();
        super.onBackPressed();

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