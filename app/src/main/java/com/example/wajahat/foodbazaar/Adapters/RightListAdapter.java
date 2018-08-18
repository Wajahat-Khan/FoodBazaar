package com.example.wajahat.foodbazaar.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wajahat.foodbazaar.Data.Items;
import com.example.wajahat.foodbazaar.R;

import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.ListViewHolder> {

    public class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView foodImage;
        private TextView title;
        private TextView description;
        private TextView price;
        private Button order_button;
        public ListViewHolder(View itemView) {
            super(itemView);
            foodImage=itemView.findViewById(R.id.food_image);
            title=itemView.findViewById(R.id.food_title);
            description=itemView.findViewById(R.id.food_description);
            price=itemView.findViewById(R.id.food_price);
            order_button=itemView.findViewById(R.id.food_order_button);
        }
    }

    private List<Items> items;
    private final LayoutInflater inflater;
    public RightListAdapter(Context context, List<Items> items){
        this.items=items;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public RightListAdapter.ListViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.right_frame_child_view,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RightListAdapter.ListViewHolder holder, int position) {
        if (items!=null) {
            Items item = items.get(position);
            holder.foodImage.setImageResource(R.drawable.beef_burger);
            holder.title.setText(item.getName());
            holder.description.setText(item.getShort_description());
        }
        }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
