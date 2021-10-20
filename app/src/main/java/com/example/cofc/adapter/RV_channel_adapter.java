package com.example.cofc.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cofc.R;
import com.example.cofc.interfaces.Link_onClick;
import com.example.cofc.model.Data_of_channels;
import com.example.cofc.model.Data_of_news;
import com.google.android.youtube.player.YouTubeIntents;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RV_channel_adapter extends RecyclerView.Adapter<RV_channel_adapter.RV_Holder> {

    private List<Data_of_channels> list = new ArrayList<>();
    private List<Data_of_channels> full_List;
    private Context c;
    private Link_onClick listener;

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }

    public Link_onClick getListener() { return listener; }
    public void setListener(Link_onClick listener) { this.listener = listener; }

    public void setList(List<Data_of_channels> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_channels> getList() {
        return list;
    }

    public RV_channel_adapter() { }
    public RV_channel_adapter(Context c , List<Data_of_channels> list) {
        this.c = c;
        this.list = list;
        full_List = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.helpful_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_channels pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Filter getFilter() {
        return myFilter;
    }

    private final Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Data_of_channels> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0)
                filteredList.addAll(full_List);
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data_of_channels item : full_List) {
                    if (item.getCourseName().toLowerCase().contains(filterPattern) || item.getChannelName().toLowerCase().contains(filterPattern))
                        filteredList.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        TextView tv_course;
        TextView tv_channel;
        ImageButton logo;
        LinearLayout layout;

        Data_of_channels p;
        public RV_Holder(View itemView) {
            super(itemView);
            tv_course = itemView.findViewById(R.id.vh_tv_course_name);
            tv_channel = itemView.findViewById(R.id.vh_tv_channel_name);
            logo = itemView.findViewById(R.id.vh_ibtn_logo);
            layout = itemView.findViewById(R.id.vh_layout);
        }

        public void bind(Data_of_channels model) {
            p = model;
            tv_course.setText(p.getCourseName());
            tv_channel.setText(p.getChannelName());
             //Set the image
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.ic_launcher_background);
            Glide.with(c)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(p.getLogo())
                    .into(logo);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent i = YouTubeIntents.createUserIntent(getC(), p.getLink());
                    c.startActivity(i);*/
                    /*Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + p.getLink()));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + p.getLink()));
                    try {
                        getC().startActivity(appIntent);
                    } catch (ActivityNotFoundException ex) {
                        getC().startActivity(webIntent);
                    }*/
                    //getC().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));


                }
            });
        }
    }
}