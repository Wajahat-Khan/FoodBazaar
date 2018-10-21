package com.example.wajahat.foodbazaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wajahat.foodbazaar.Adapters.OrderAdapter;
import com.example.wajahat.foodbazaar.Data.Order;


public class OrderActivity extends AppCompatActivity {
    Order order;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        order=(Order) bundle.getSerializable("order_list");

        TextView title=(TextView) findViewById(R.id.table_no);
        title.append(Integer.toString(order.getTable_no()));

        recyclerView=findViewById(R.id.order_recycler_view);
        orderAdapter=new OrderAdapter(this, order);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
