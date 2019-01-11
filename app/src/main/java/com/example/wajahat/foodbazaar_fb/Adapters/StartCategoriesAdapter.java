package com.example.wajahat.foodbazaar_fb.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wajahat.foodbazaar_fb.Data.Categories;
import com.example.wajahat.foodbazaar_fb.R;

import java.util.List;

public class StartCategoriesAdapter extends RecyclerView.Adapter<StartCategoriesAdapter.ViewHolder> {

    public interface AdapterListener { void onClick(View view, int position, List<Categories> items);

        void onClick(View catView, int adapterPosition);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
       private ImageView cat_button;
        public ViewHolder(View catView) {
            super(catView);

            cat_button=catView.findViewById(R.id.category_icon);
            cat_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myAdapterListener.onClick(view, getAdapterPosition(),getAllCategories());
                }
            });
        }
    }
    private final LayoutInflater inflater;
    private List<Categories> allCategories;
    public AdapterListener myAdapterListener;
    private Context context;

    public StartCategoriesAdapter(Context context, AdapterListener listener){
        inflater=LayoutInflater.from(context);
        myAdapterListener=listener;
        this.context=context;
    }
    @Override
    public StartCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.start_activity_recyler_view,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(StartCategoriesAdapter.ViewHolder holder, int position) {
        if(allCategories!=null){
            Categories current=allCategories.get(position);
            Glide.with(context).load(current.getPicture()).into(holder.cat_button);
        }
        else{
            Toast.makeText(context, "no cats", Toast.LENGTH_SHORT).show();
        }

    }
    public void setCategories(List<Categories> categories){
        this.allCategories=categories;
        notifyDataSetChanged();

    }

    public List<Categories> getAllCategories(){
        return allCategories;
    }

    @Override
    public int getItemCount() {
        if(allCategories!=null){
            return allCategories.size();
        }
        else return 0;
    }
}