package com.example.cofc.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cofc.R;
import com.example.cofc.model.Data_of_package;
import com.example.cofc.special_model.S_addToCart;

import java.util.ArrayList;
import java.util.List;

public class RV_package_adapter extends RecyclerView.Adapter<RV_package_adapter.RV_Holder> {

    private List<Data_of_package> list = new ArrayList<>();
    List<S_addToCart.PackageID> chosen_packages = new ArrayList<>();

    public List<S_addToCart.PackageID> getChosen_packages() {
        return chosen_packages;
    }

    public void setList(List<Data_of_package> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_package> getList() {
        return list;
    }

    public RV_package_adapter() { }
    public RV_package_adapter(List<Data_of_package> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apparent items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_package, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_package pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        CheckBox cb;
        RecyclerView rv;
        RV_item_adapter itemAdapter;
        TextView tv;
        LinearLayout ll;

        Data_of_package p;
        public RV_Holder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb_package_no);
            rv = itemView.findViewById(R.id.rv_components);
            tv = itemView.findViewById(R.id.tv_cost);
            ll = itemView.findViewById(R.id.triple_l);

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rv.getVisibility() == View.VISIBLE)
                        rv.setVisibility(View.GONE);
                    else
                        rv.setVisibility(View.VISIBLE);
                }
            });
        }

        public void bind(Data_of_package model) {
            p = model;
            cb.append(String.valueOf(p.getPackageNumber()));

            itemAdapter = new RV_item_adapter(p.getComponents());
            rv.setAdapter(itemAdapter);
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
            rv.setHasFixedSize(true);
            rv.setItemAnimator(new DefaultItemAnimator());

            tv.append(new StringBuilder().append(p.getPackageCost()).append(" L.E").toString()); //+ R.string.currency

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        chosen_packages.add(new S_addToCart.PackageID(p.getPackageID()));
                        ll.setBackgroundResource(android.R.color.holo_blue_light);
                    }
                    else{
                        chosen_packages.remove(((Object) p.getPackageID()));
                        ll.setBackgroundResource(android.R.color.white);
                    }
                }
            });
        }
    }
}