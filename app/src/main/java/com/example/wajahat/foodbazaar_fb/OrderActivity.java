package com.example.wajahat.foodbazaar_fb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wajahat.foodbazaar_fb.Adapters.OrderAdapter;
import com.example.wajahat.foodbazaar_fb.Data.Items;
import com.example.wajahat.foodbazaar_fb.Data.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    Order order=new Order();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Items> ordered_items_list=new ArrayList<>();

    private Items temp_item;
    int total;
    TextView tt;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        order = (Order) bundle.getSerializable("order_list");
        ordered_items_list = order.getOrder_list();
        total=order.getTotal();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Orders");
        databaseReference.keepSynced(true);

        TextView title = (TextView) findViewById(R.id.table_no);
        title.append(Integer.toString(order.getTable_no()));

        recyclerView = findViewById(R.id.order_recycler_view);

        orderAdapter = new OrderAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter.set_order(ordered_items_list);


        tt=findViewById(R.id.order_total);
        tt.setText(Integer.toString(total));

        Button order_confirmed=findViewById(R.id.confirm_order);
        order_confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(order);
                Order ordernew=new Order();
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }



}