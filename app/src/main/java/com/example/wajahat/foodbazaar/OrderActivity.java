package com.example.wajahat.foodbazaar;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wajahat.foodbazaar.Adapters.OrderAdapter;
import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.Data.Order;
import com.example.wajahat.foodbazaar.Database.FoodbazaarRepository;
import com.example.wajahat.foodbazaar.Interfaces.AsyncResponse;
import com.example.wajahat.foodbazaar.ViewModels.ItemsViewModel;

import java.util.HashMap;
import java.util.Map;


public class OrderActivity extends AppCompatActivity {
    Order order;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private FoodbazaarRepository foodbazaarRepository;
    private ItemsViewModel itemsViewModel;
    private HashMap<Items, Integer> ordered_items_list=new HashMap<>();
    private HashMap<Integer, Integer> temp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        order = (Order) bundle.getSerializable("order_list");
        temp = order.getOrder_list();
        itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel.class);


        TextView title = (TextView) findViewById(R.id.table_no);
        title.append(Integer.toString(order.getTable_no()));

        recyclerView = findViewById(R.id.order_recycler_view);
        new PopulateDbAsync(temp).execute(temp);

        orderAdapter = new OrderAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter.set_order(ordered_items_list);
    }



    private class PopulateDbAsync extends AsyncTask<HashMap<Integer,Integer>, Void, HashMap<Items, Integer>> {

        private HashMap<Integer,Integer> ord;
        public AsyncResponse delegate=null;
        public PopulateDbAsync(HashMap<Integer,Integer> mp)
        {
            this.ord=mp;

        }
        @Override
        protected HashMap<Items, Integer> doInBackground(HashMap<Integer,Integer>... voids) {

            for ( final Map.Entry<Integer, Integer> entry :ord.entrySet() ) {

                ordered_items_list.put(itemsViewModel.getItemById(entry.getKey()), entry.getValue());
            }

            return ordered_items_list;
        }

        @Override
        protected void onPostExecute(HashMap<Items, Integer> itemsIntegerHashMap) {
            super.onPostExecute(itemsIntegerHashMap);
        }
    }
}
