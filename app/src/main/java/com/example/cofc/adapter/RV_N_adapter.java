package com.example.cofc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.R;
import com.example.cofc.interfaces.NewClickListener;
import com.example.cofc.model.Data_of_news;
import com.example.cofc.model.M_MainCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RV_N_adapter extends RecyclerView.Adapter<RV_N_adapter.RV_holder> /*implements Filterable*/ {

    private List<Data_of_news> arrayList = new ArrayList<>();
    private List<Data_of_news> full_List;
    NewClickListener listener;
    Context c;

    public List<Data_of_news> getList() { return arrayList; }
    public void setList(List<Data_of_news> arrayList) { this.arrayList = arrayList; }
    public NewClickListener getListener() { return listener; }
    public void setListener(NewClickListener listener) { this.listener = listener; }
    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }

    public RV_N_adapter() {
    }
    public RV_N_adapter(Context c , List<Data_of_news> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
        full_List = arrayList;
    }

    @Override
    public RV_N_adapter.RV_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view,parent,false);
        return new RV_N_adapter.RV_holder(v);
    }

    @Override
    public void onBindViewHolder(RV_N_adapter.RV_holder holder, int position) {
        Data_of_news d = arrayList.get(position);
        holder.bind(d);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /*@Override
    public Filter getFilter() {
        return myFilter;
    }

    private final Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Data_of_news> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(full_List);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data_of_news item : full_List) {
                    if (item.getCourse_name().toLowerCase().contains(filterPattern))
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
            arrayList.addAll((Collection<? extends Data_of_news>) results.values);
            notifyDataSetChanged();
        }
    };*/

    public class RV_holder extends RecyclerView.ViewHolder {
        TextView course_name;
        TextView instructor_name;
        TextView center_name;
        TextView modified_date;
        TextView what_is_new;
        ImageButton logo;
        LinearLayout l;

        Data_of_news data;
        int count = 0;
        int h;
        public RV_holder(View itemView) {
            super(itemView);
            l = itemView.findViewById(R.id.vn_all);
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(data);
                }
            });
            course_name = itemView.findViewById(R.id.tv_n_course_name);
            instructor_name = itemView.findViewById(R.id.tv_n_instructor_name);
            center_name = itemView.findViewById(R.id.tv_n_center_name);
            modified_date = itemView.findViewById(R.id.tv_modified_date);
            what_is_new = itemView.findViewById(R.id.tv_what_is_new);
            what_is_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewGroup.LayoutParams params = what_is_new.getLayoutParams();
                    if (count == 0){
                        h = params.height;
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        what_is_new.setLayoutParams(params);
                        count++;
                    }
                    else{
                        params.height = h ; //R.dimen.tv_height_24sp;
                        what_is_new.setLayoutParams(params);
                        count--;
                    }
                }
            });
            logo = itemView.findViewById(R.id.vn_ibtn_logo);
        }

        public void bind(Data_of_news data)
        {
            this.data = data;

            course_name.setText(data.getCourse_name());

            if(data.getInstructor_name() != null){
                instructor_name.setText(data.getInstructor_name());
                center_name.setVisibility(View.GONE);
            }
            if(data.getInstructor_name() != null){
                center_name.setText(data.getCenter_name());
                instructor_name.setVisibility(View.GONE);
            }

            modified_date.setText(data.getModified_date());
            what_is_new.setText(data.getWhat_is_new());

            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.ic_launcher_background);
            Glide.with(c)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(data.getLogo())//which url
                    .into(logo);//which view

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(data);
                }
            });
            l.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(data);
                    return true;
                }
            });

        }
    }

}

