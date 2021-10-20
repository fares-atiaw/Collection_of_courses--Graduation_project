package com.example.cofc.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cofc.R;
import com.example.cofc.interfaces.VideoItem_onClick;
import com.example.cofc.model.Data_of_availableCourses;
import com.example.cofc.model.Data_of_component;
import com.example.cofc.model.Data_of_video;
import com.example.cofc.ui.activity.AvailableCourse;
import com.example.cofc.ui.activity.CourseDetails;
import com.example.cofc.ui.activity.Video_Comment;

import java.util.ArrayList;
import java.util.List;

public class RV_availables_adapter extends RecyclerView.Adapter<RV_availables_adapter.RV_Holder> {

    private List<Data_of_availableCourses> list = new ArrayList<>();
    VideoItem_onClick listener;

    public void setList(List<Data_of_availableCourses> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_availableCourses> getList() {
        return list;
    }

    public VideoItem_onClick getListener() { return listener; }
    public void setListener(VideoItem_onClick listener) { this.listener = listener; }

    public RV_availables_adapter() {
    }
    public RV_availables_adapter(List<Data_of_availableCourses> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apparent items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_available_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_availableCourses pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        Button name;
        LinearLayout layout;
        LinearLayout ll;
        RecyclerView rv;
        RV_component_adapter adapter;
        //private List<Data_of_component> Components;
        Data_of_availableCourses p;

        public RV_Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vac_btn_course_name);
            layout = itemView.findViewById(R.id.vac_layout_components);
            rv = itemView.findViewById(R.id.vac_rv_components);
        }

        public void bind(Data_of_availableCourses model) {
            p = model;
            name.setText(p.getCourseName());
            adapter = new RV_component_adapter(p.getComponents());
            adapter.setListener(new VideoItem_onClick() {
                @Override
                public void click(Data_of_video data) {
                    listener.click(data);
                }
            });
            rv.setAdapter(adapter);
            //rv.setHasFixedSize(true);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.setVisibility(View.VISIBLE);
                    if(ll != null)
                        ll.setVisibility(View.GONE);
                    ll=layout;
                }
            });
        }
    }
}