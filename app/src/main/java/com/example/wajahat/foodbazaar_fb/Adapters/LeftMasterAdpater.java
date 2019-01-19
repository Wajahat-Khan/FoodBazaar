package com.example.wajahat.foodbazaar_fb.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wajahat.foodbazaar_fb.R;


public class LeftMasterAdpater extends RecyclerView.Adapter<LeftMasterAdpater.LeftViewHolder> {

    public interface LeftMasterListener { void onClick(View view, int position, String[] categories);
    }
    public class LeftViewHolder extends RecyclerView.ViewHolder{
        private Button cat_button;

        public LeftViewHolder(View catView) {
            super(catView);

            cat_button=catView.findViewById(R.id.left_item_btn);
            cat_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   leftMaster.onClick(view, getAdapterPosition(),categories);
                }
            });
        }
    }

    public LeftMasterListener leftMaster;
    private final LayoutInflater inflater;
    private String[] categories;
    private String main_category;
    public LeftMasterAdpater(Context context, String cat, LeftMasterListener leftMasterListener){
        inflater=LayoutInflater.from(context);
        this.leftMaster=leftMasterListener;
        this.main_category=cat;
    }
    @Override
    public LeftMasterAdpater.LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.left_frame_child_view,parent,false);
        return new LeftViewHolder(item);

    }

    @Override
    public void onBindViewHolder(LeftMasterAdpater.LeftViewHolder holder, int position) {
        if(categories!=null){
            String current=categories[position];
            if(current.equals(main_category)){
                holder.cat_button.setBackgroundColor(Color.parseColor("#D4AF37"));
                holder.cat_button.setTextColor(Color.parseColor("#DCDCDC"));

            }
            holder.cat_button.setText(current);
        }

    }
    public void setCategories(String[] categories){
        this.categories=categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(categories!=null){
            return categories.length;
        }
        else return 0;
    }
}
