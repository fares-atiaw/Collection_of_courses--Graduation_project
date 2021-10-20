package com.example.cofc.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cofc.R;
import com.example.cofc.model.Data_of_package;

import java.util.ArrayList;
import java.util.List;

public class RV_item_adapter extends RecyclerView.Adapter<RV_item_adapter.RV_Holder> {

    private List<Data_of_package.Component> list = new ArrayList<>();
    /*private Item_onClick listener;

    public Item_onClick getListener() { return listener; }
    public void setListener(Item_onClick listener) { this.listener = listener; }*/

    public void setList(List<Data_of_package.Component> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_package.Component> getList() {
        return list;
    }

    public RV_item_adapter() {
    }
    public RV_item_adapter(List<Data_of_package.Component> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.only_name, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_package.Component pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        Button btn;
        Data_of_package.Component p;
        public RV_Holder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_name_only);
        }
        public void bind(Data_of_package.Component model) {
            p = model;
            btn.setText(p.getComponentName());
        }
    }

}