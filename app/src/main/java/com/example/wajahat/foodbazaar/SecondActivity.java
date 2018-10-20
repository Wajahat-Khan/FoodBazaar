package com.example.wajahat.foodbazaar;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import com.example.wajahat.foodbazaar.Adapters.LeftMasterAdpater;
import com.example.wajahat.foodbazaar.Adapters.RightListAdapter;
import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.Data.Order;
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
    private  HashMap<Integer,Integer> order_items_map=new HashMap<>();
    Order order_object=new Order();
    final int default_quantity=1;
    /*
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
       */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        order_object=(Order) bundle.getSerializable("order_list");
        order_items_map=order_object.getOrder_list();

        rightRecylcerView=findViewById(R.id.right_frame_recycler_view);
        leftRecyclerView=findViewById(R.id.left_frame_recycler_view);

        rightListAdapter  = new RightListAdapter(this, new RightListAdapter.MyAdapterListener() {
            @Override
            public void onClick(View view, int position, List<Items> it) {
               items=it;
               Items temp=items.get(position);
               if(order_items_map.containsKey(temp.getId())) {
                    int curr=order_items_map.get(temp.getId());
                    curr++;
                    order_items_map.put(temp.getId(),curr);
               }
               else
               {order_items_map.put(temp.getId(),default_quantity);
               }
                Snackbar.make(view, temp.getName() + "ordered" + order_items_map.get(temp.getId())+ "times", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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


    }

    @Override
    public void onBackPressed() {
        order_object.setOrder_list(order_items_map);
        Intent intent=new Intent();
        intent.putExtra("order_list",order_object);
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
