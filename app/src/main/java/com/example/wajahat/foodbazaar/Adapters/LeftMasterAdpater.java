package com.example.wajahat.foodbazaar.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wajahat.foodbazaar.R;
import com.example.wajahat.foodbazaar.Word;

import java.util.List;

public class LeftMasterAdpater extends RecyclerView.Adapter<LeftMasterAdpater.MasterViewHolder> {

    public class MasterViewHolder extends RecyclerView.ViewHolder{
        private Button btn;
        public MasterViewHolder(View view){
            super(view);
            btn=view.findViewById(R.id.left_item_btn);
        }

    }
    private List<Word> words;
    private final LayoutInflater inflater;

    public LeftMasterAdpater(Context context, List<Word> w){
        //Toast.makeText(context, "valled"+getItemCount(), Toast.LENGTH_SHORT).show();
        inflater=LayoutInflater.from(context);
        words=w;
         }

    @Override
    public LeftMasterAdpater.MasterViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.left_frame_child_view,parent,false);
        return new MasterViewHolder(item);
    }

    @Override
    public void onBindViewHolder( MasterViewHolder holder, int position) {
        if(words!=null) {
            Word wo = words.get(position);
            holder.btn.setText(wo.getWord().toString());
        }
        else
        {
            holder.btn.setText("No items");
        }
        }

    @Override
    public int getItemCount() {
        if(words!=null){
            return words.size();
        }
        else return 0;
    }

}
