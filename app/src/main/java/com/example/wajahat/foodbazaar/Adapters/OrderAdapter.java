package com.example.wajahat.foodbazaar.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wajahat.foodbazaar.Data.Order;
import com.example.wajahat.foodbazaar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView item;
        private TextView quantity;
        private TextView price;
        private TextView total;

        public OrderViewHolder(View ordered_item) {
            super(ordered_item);
            item=ordered_item.findViewById(R.id.order_item);
            quantity=ordered_item.findViewById(R.id.order_quantity);
            price=ordered_item.findViewById(R.id.order_price);
            total=ordered_item.findViewById(R.id.order_total);
        }
    }

    private final LayoutInflater inflater;
    private Order order;
    private HashMap<Integer,Integer> order_map;
    List<Integer> items = new ArrayList<>();
    List<Integer> count = new ArrayList<>();
    public OrderAdapter(Context context, Order order){
        inflater= LayoutInflater.from(context);
        this.order=order;
        order_map=order.getOrder_list();

        for ( Map.Entry<Integer,Integer> entry : order_map.entrySet()) {
                    items.add(entry.getKey());
                    count.add(entry.getValue());
            }

    }


    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ordered_item=inflater.inflate(R.layout.order_activity_recycler_view,parent,false);
        return new OrderViewHolder(ordered_item);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.OrderViewHolder holder, int position) {

        if(items!=null){
            int key=items.get(position);
            int quant=count.get(position);
            holder.item.setText(Integer.toString(key));
            Log.d("item",Integer.toString(key));
            holder.quantity.setText(Integer.toString(quant));
            Log.d("quantity",Integer.toString(quant));

        }

    }

    @Override
    public int getItemCount() {
        if(items!=null){
            return items.size();
        }
        else return 0;
    }
}
