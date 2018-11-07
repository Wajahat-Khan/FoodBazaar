package com.example.wajahat.foodbazaar_fb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wajahat.foodbazaar_fb.Data.Items;
import com.example.wajahat.foodbazaar_fb.Data.Order;
import com.example.wajahat.foodbazaar_fb.R;

import java.util.List;

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
    Context context;
    private List<Items> ordered_items_list;

    public OrderAdapter(Context context){
        inflater= LayoutInflater.from(context);
        this.context=context;
    }


    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ordered_item=inflater.inflate(R.layout.order_activity_recycler_view,parent,false);
        return new OrderViewHolder(ordered_item);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.OrderViewHolder holder, int position) {

        if(ordered_items_list!=null){
            Items temp=ordered_items_list.get(position);
            int quant=temp.getQuantity();
            holder.item.setText(temp.getName());
            holder.quantity.setText(Integer.toString(quant));
            holder.price.setText(Integer.toString(temp.getPrice()));
            holder.total.setText(Integer.toString(temp.getPrice()*quant));

        }

    }

    @Override
    public int getItemCount() {
        if(ordered_items_list!=null){
            return ordered_items_list.size();
        }
        else return 0;
    }

    public void set_order(List<Items> abc){
        this.ordered_items_list=abc;
        notifyDataSetChanged();

    }
}

