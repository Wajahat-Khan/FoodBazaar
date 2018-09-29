package com.example.wajahat.foodbazaar.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wajahat.foodbazaar.Data.Categories;
import com.example.wajahat.foodbazaar.R;

import java.util.List;

public class LeftMasterAdpater extends RecyclerView.Adapter<LeftMasterAdpater.LeftViewHolder> {

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        private Button cat_button;
        public LeftViewHolder(View catView) {
            super(catView);

            cat_button=catView.findViewById(R.id.left_item_btn);
        }
    }
    private final LayoutInflater inflater;
    private List<Categories> categories;
    public LeftMasterAdpater(Context context){
        inflater=LayoutInflater.from(context);
    }
    @Override
    public LeftMasterAdpater.LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.left_frame_child_view,parent,false);
        return new LeftViewHolder(item);
    }

    @Override
    public void onBindViewHolder(LeftMasterAdpater.LeftViewHolder holder, int position) {
        if(categories!=null){
            Categories current=categories.get(position);
            holder.cat_button.setText(current.getName().toString());
        }

    }
    public void setCategories(List<Categories> categories){
        this.categories=categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(categories!=null){
            return categories.size();
        }
        else return 0;
    }
}
