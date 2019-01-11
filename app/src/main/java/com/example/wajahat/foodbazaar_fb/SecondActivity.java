package com.example.wajahat.foodbazaar_fb;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wajahat.foodbazaar_fb.Adapters.LeftMasterAdpater;
import com.example.wajahat.foodbazaar_fb.Adapters.RightListAdapter;
import com.example.wajahat.foodbazaar_fb.Data.Items;
import com.example.wajahat.foodbazaar_fb.Data.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<Items> items=new ArrayList<>();

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecylcerView;
    private LeftMasterAdpater leftMasterAdpater;
    private RightListAdapter rightListAdapter;
    private String category_selected;
    private String[] sub_categories;
    private int Total;
    private int index;

    final int default_quantity=1;
    private int quantity;
    Order order_object;
    List<Items> order_list;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        View bkg=findViewById(R.id.main_second);
        bkg.setBackgroundColor(Color.parseColor("#000000"));
        bkg.setAlpha((float) 0.8);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        order_object= (Order) bundle.get("order_list");
        Total=order_object.getTotal();
        order_list=order_object.getOrder_list();
        if(order_list==null){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }
        else{
            for (int h=0;h<order_list.size();h++){
                Toast.makeText(this, order_list.get(h).getName()+order_list.get(h).getQuantity(), Toast.LENGTH_SHORT).show();
            }
        }
        category_selected=bundle.getString("category");
        Total=order_object.getTotal();

        // Initialize Firebase components

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Items");
        databaseReference.keepSynced(true);


        rightRecylcerView=findViewById(R.id.right_frame_recycler_view);
        leftRecyclerView=findViewById(R.id.left_frame_recycler_view);

        rightListAdapter  = new RightListAdapter(this, new RightListAdapter.MyAdapterListener() {
            @Override
            public void onClick(View view, int position, List<Items> it) {
                items=it;
                int check_flag=0;
                Items temp=items.get(position);
                String item_name=temp.getName();
                for( Items iterator : order_list) {
                    if (iterator.getName().equals(item_name)) {
                        quantity = iterator.getQuantity();
                        quantity++;
                        iterator.setQuantity(quantity);
                        Total += temp.getPrice();
                        check_flag=1;
                        Toast.makeText(SecondActivity.this, temp.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    }
                }

                if(check_flag==0){
                    temp.setQuantity(default_quantity);
                    Total+=temp.getPrice();
                    order_list.add(temp);
                    Toast.makeText(SecondActivity.this, temp.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                }


                /*if(order_list.contains(temp)) {
                    index=order_list.indexOf(temp);
                    temp=order_list.get(index);
                    quantity=temp.getQuantity();
                    quantity++;
                    temp.setQuantity(quantity);
                    order_list.remove(index);
                    order_list.add(temp);
                    Total+=temp.getPrice();
                    Toast.makeText(SecondActivity.this, temp.getName()+ " added to cart", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
        rightRecylcerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rightRecylcerView.setAdapter(rightListAdapter);
        rightRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        leftMasterAdpater=new LeftMasterAdpater(this, category_selected, new LeftMasterAdpater.LeftMasterListener() {
            @Override
            public void onClick(View view, int position, String[] categories) {
                sub_categories=categories;
                String sub_cat=sub_categories[position];
                if(sub_cat.equals(category_selected)) {
                    rightListAdapter.setItems(items);
                }
                final List<Items> new_it=new ArrayList<>();
                databaseReference.orderByChild("sub_category").equalTo(sub_cat).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Items item=dataSnapshot.getValue(Items.class);
                        // Toast.makeText(SecondActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                        new_it.add(item);
                        rightListAdapter.setItems(new_it);
                        //rightListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });

            }
        });
        leftRecyclerView.setAdapter(leftMasterAdpater);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        findViewById(R.id.order_now).setOnClickListener(new View.OnClickListener() {
            String table_no="";
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(SecondActivity.this);
                final EditText editText = new EditText(SecondActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
                editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                dialog.setTitle("Table No: ")
                        .setPositiveButton("ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(SecondActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();

                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                        order_object.setOrder_list(order_list);
                                        order_object.setTable_no(Integer.parseInt(editText.getText().toString()));
                                        order_object.setTotal(Total);
                                        Intent intent=new Intent(getBaseContext(),OrderActivity.class);
                                        intent.putExtra("order_list", order_object);
                                        startActivityForResult(intent,1);
                                    }
                                });

                dialog.setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                            }
                        });

                dialog.setView(editText);
                dialog.create().show();

                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }
        });
        leftMasterAdpater.setCategories(getIntent().getStringArrayExtra("subCategories"));
        databaseReference.orderByChild("major_category").equalTo(category_selected).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Items item=dataSnapshot.getValue(Items.class);
                items.add(item);
                rightListAdapter.setItems(items);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        order_object.setOrder_list(order_list);
        order_object.setTotal(Total);
        Intent intent=new Intent();
        intent.putExtra("order_list", (Serializable) order_object);
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
