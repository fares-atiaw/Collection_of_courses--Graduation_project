package com.example.cofc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.R;
import com.example.cofc.interfaces.Listener_ID;
import com.example.cofc.model.M_MainCategory;
import com.example.cofc.model.M_SubCategory;

import java.util.ArrayList;
import java.util.List;

public class RV_SubCategory_adapter extends RecyclerView.Adapter<RV_SubCategory_adapter.RV_holder> implements Filterable {

    private List<M_SubCategory> arrayList = new ArrayList<>();
    private List<M_SubCategory> full_List;
    Context c;
    private Listener_ID Listener;

    public RV_SubCategory_adapter(Context c){
        this.c = c;
    }
    public RV_SubCategory_adapter(Context c , List<M_SubCategory> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
        full_List = arrayList;
    }

    public List<M_SubCategory> getArrayList() { return arrayList; }
    public void setList(List<M_SubCategory> arrayList) { this.arrayList = arrayList; }
    public Listener_ID getListener() { return Listener; }
    public void setListener(Listener_ID listener) { Listener = listener; }

    @NonNull
    @Override
    public RV_SubCategory_adapter.RV_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category,parent,false);
        return new RV_SubCategory_adapter.RV_holder(v);
    }

    @Override
    public void onBindViewHolder(RV_SubCategory_adapter.RV_holder holder, int position) {
        M_SubCategory d = arrayList.get(position);
        holder.Bind(d);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private final Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<M_SubCategory> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(full_List);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (M_SubCategory item : full_List) {
                    if (item.getSubCategoryName().toLowerCase().contains(filterPattern))
                        filteredList.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class RV_holder extends RecyclerView.ViewHolder
    {
        TextView tv;
        ImageButton logo;

        M_SubCategory category;
        public RV_holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.vc_tv_name);
            logo = itemView.findViewById(R.id.vc_ibtn_logo);
        }
        void Bind(M_SubCategory category)
        {
            this.category=category;

            tv.setText(category.getSubCategoryName());
            // Set the image
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.animated_book);
            Glide.with(c)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(category.getSubCategoryLogo())//which url
                    .into(logo);//which view

            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Listener.onClick(category.getSubCategoryID() , category.getSubCategoryName());
                }
            });
            logo.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Listener.onLongClick(category.getSubCategoryID() , category.getSubCategoryName() , null , getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
