package com.example.cofc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cofc.R;

import java.util.ArrayList;
import java.util.List;

public class RV_num_adapter extends RecyclerView.Adapter<RV_num_adapter.RV_Holder> {

    private List<Integer> list = new ArrayList<>();

    public void setList(List<Integer> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Integer> getList() {
        return list;
    }

    public RV_num_adapter(List<Integer> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.only_num, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Integer pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public static class RV_Holder extends RecyclerView.ViewHolder {
        TextView tv;
        Integer p;

        public RV_Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_num_only);
        }

        public void bind(Integer model) {
            p = model;
            tv.append(String.valueOf(p));

        }
    }
}