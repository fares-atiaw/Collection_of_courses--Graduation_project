package com.example.cofc.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cofc.R;
import com.example.cofc.model.Data_of_package;
import com.example.cofc.model.Data_of_purchase;

import java.util.ArrayList;
import java.util.List;

public class RV_purchase_adapter extends RecyclerView.Adapter<RV_purchase_adapter.RV_Holder> {

    private List<Data_of_purchase> list;
    PackagesChanged listener;

    public PackagesChanged getListener() { return listener; }
    public void setListener(PackagesChanged listener) { this.listener = listener; }

    public void setList(List<Data_of_purchase> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_purchase> getList() {
        return list;
    }

    public RV_purchase_adapter(List<Data_of_purchase> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_purchase_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_purchase p = list.get(position);
        holder.bind(p);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        TextView tv_name;
        RecyclerView rv;
        RV_num_adapter num_adapter;
        TextView tv_price;
        ImageButton btn_close;
        Data_of_purchase p;
        List<Integer> nums = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        double full_price = 0;
        public RV_Holder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.vpi_tv_course_name);
            rv = itemView.findViewById(R.id.vpi_rv_packages);
            tv_price = itemView.findViewById(R.id.vpi_tv_price);
            btn_close = itemView.findViewById(R.id.vpi_iv_close);
        }
        public void bind(Data_of_purchase model) {
            p = model;

            tv_name.setText(p.getCourse_name());

            for (int i = 0 ; i<= p.getPackages().size()-1 ; i++)
            {
                nums.add(p.getPackages().get(i).getPackageNumber());
            }
            num_adapter = new RV_num_adapter(nums);
            rv.setAdapter(num_adapter);
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setHasFixedSize(true);

            for (int i = 0 ; i<= p.getPackages().size()-1 ; i++)
            {
                full_price += p.getPackages().get(i).getPackageCost();
            }
            tv_price.setText(new StringBuilder().append(" ").append(full_price).append(" L.E").toString());

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    for (int i = 0 ; i <= p.getPackages().size()-1 ; i++) {
                        ids.add(p.getPackages().get(i).getPackageID());
                    }
                    listener.onListChange(p.getCourse_name() , ids , full_price);
                }
            });
        }
    }

    public interface PackagesChanged
    {
        void onListChange(String course_name , List<Integer> ids , double price);
    }
}