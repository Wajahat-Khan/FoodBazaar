package com.example.wajahat.foodbazaar_fb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wajahat.foodbazaar_fb.Data.Items;
import com.example.wajahat.foodbazaar_fb.R;

import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.RightViewHolder> {
    public interface MyAdapterListener { void onClick(View view, int position, List<Items> items);
    }

    public class RightViewHolder extends RecyclerView.ViewHolder {
        private ImageView foodImage;
        private TextView title;
        private TextView description;
        private TextView price;
        private Button order_button;

        public RightViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image);
            title = itemView.findViewById(R.id.food_title);
            description = itemView.findViewById(R.id.food_description);
            price = itemView.findViewById(R.id.food_price);
            order_button = itemView.findViewById(R.id.food_order_button);
            order_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myAdapterListener.onClick(view, getAdapterPosition(),getItems());
                }
            });
        }
    }

    public MyAdapterListener myAdapterListener;
    private final LayoutInflater inflater;
    private List<Items> items;
    Context co;
    public RightListAdapter(Context context, MyAdapterListener listener){
        inflater=LayoutInflater.from(context);
        this.myAdapterListener=listener;
        co=context;
    }

    @Override
    public RightListAdapter.RightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.right_frame_child_view,parent,false);
        return new RightViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RightListAdapter.RightViewHolder holder, int position) {
        if(items!=null){
            Items current=items.get(position);
             Glide.with(co).load(current.getPicture()).into(holder.foodImage);
            //holder.foodImage.setImageResource(R.drawable.beef_burger);
            holder.title.setText(current.getName());
            holder.description.setText(current.getShort_description());
            holder.price.setText(Integer.toString(current.getPrice()));
        }

    }
    public void setItems(List<Items> words){
        this.items=words;
        notifyDataSetChanged();
    }
    public List<Items> getItems(){
        return items;
    }

    @Override
    public int getItemCount() {
        if(items!=null){
            return items.size();
        }
        else return 0;
    }


}

