package com.example.cofc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.R;
import com.example.cofc.interfaces.CourseClickListener;
import com.example.cofc.model.Data_of_course;
import com.example.cofc.model.M_MainCategory;

import java.util.ArrayList;
import java.util.List;

public class RV_C_adapter extends RecyclerView.Adapter<RV_C_adapter.RV_holder> /*implements Filterable*/ {

    private List<Data_of_course> arrayList = new ArrayList<>();
    private List<Data_of_course> full_List;
    CourseClickListener listener;
    Context c;

    public List<Data_of_course> getArrayList() { return arrayList; }
    public void setList(List<Data_of_course> arrayList) { this.arrayList = arrayList; }
    public CourseClickListener getListener() { return listener; }
    public void setListener(CourseClickListener listener) { this.listener = listener; }

    public RV_C_adapter() {
    }
    public RV_C_adapter(Context c , List<Data_of_course> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
        full_List = arrayList;
    }

    @Override
    public RV_holder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view,parent,false);
        return new RV_holder(v);
    }

    @Override
    public void onBindViewHolder(RV_C_adapter.RV_holder holder, int position) {
        Data_of_course d = arrayList.get(position);
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
            List<Data_of_course> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(full_List);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data_of_course item : full_List) {
                    if (item.getCourseName().toLowerCase().contains(filterPattern) ||
                            item.getInstructorName().toLowerCase().contains(filterPattern) ||
                            item.getCenterName().toLowerCase().contains(filterPattern))
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
    };*/

    public class RV_holder extends RecyclerView.ViewHolder {
        ImageButton logo;
        TextView course_name;
        TextView release_date;
        TextView instructor_name;
        TextView center_name;
        ImageView following_rate;
        TextView online;
        TextView offline;
        TextView address;
        TextView price;
        TextView price_options;
        RatingBar stars;
        Data_of_course data;

        public RV_holder(View itemView) {
            super(itemView);
             course_name = itemView.findViewById(R.id.tv_course_name);
             instructor_name = itemView.findViewById(R.id.tv_instructor_name);
             center_name = itemView.findViewById(R.id.tv_center_name);
             release_date = itemView.findViewById(R.id.tv_release_date);
             following_rate = itemView.findViewById(R.id.iv_following_rate);
             online = itemView.findViewById(R.id.tv_online);
             offline = itemView.findViewById(R.id.tv_offline);
             address = itemView.findViewById(R.id.tv_address);
             price = itemView.findViewById(R.id.tv_price);
             price_options = itemView.findViewById(R.id.tv_options);
             stars = itemView.findViewById(R.id.vc_stars);
             logo = itemView.findViewById(R.id.vc_ibtn_logo);
        }

        public void bind(Data_of_course data)
        {
            this.data = data;

            course_name.setText(data.getCourseName());
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.following_small_rate);
            Glide.with(c)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(data.getLogo())//which url
                    .into(logo);//which view
            if(data.getInstructorName() == null)
                instructor_name.setVisibility(View.GONE);
            else{
                instructor_name.setVisibility(View.VISIBLE);
                instructor_name.setText(data.getInstructorName());
            }
            if(data.getCenterName() == null)
                center_name.setVisibility(View.GONE);
            else{
                center_name.setVisibility(View.VISIBLE);
                center_name.setText(data.getInstructorName());
            }
            release_date.setText(data.getRelasedDate());
            Glide.with(c)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(data.getqA_Following())//which url
                    .into(following_rate);//which view
            if (data.isOnline())
                online.setVisibility(View.VISIBLE);
            else
                online.setVisibility(View.GONE);
            if (data.getAddress() != null){       //offline
                offline.setVisibility(View.VISIBLE);
                address.setText(data.getAddress());
            }
            else
                offline.setVisibility(View.GONE);
            price.setText(data.getPrice().toString());  //new StringBuilder().append(data.getPrice()).append(" L.E").toString());
            if(data.isOptions())
                price_options.setVisibility(View.VISIBLE);
            else
                price_options.setVisibility(View.GONE);
            stars.setRating(data.getStars());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(data);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(data , getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
