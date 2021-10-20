package com.example.cofc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cofc.R;
import com.example.cofc.interfaces.VideoItem_onClick;
import com.example.cofc.model.Data_of_video;

import java.util.ArrayList;
import java.util.List;

public class RV_video_adapter extends RecyclerView.Adapter<RV_video_adapter.RV_Holder> {

    private List<Data_of_video> list = new ArrayList<>();
    private Context c;
    VideoItem_onClick listener;
//
    public VideoItem_onClick getListener() { return listener; }
    public void setListener(VideoItem_onClick listener) { this.listener = listener; }

    public Context getC() { return c; }
    public void setC(Context c) { this.c = c; }

    public void setList(List<Data_of_video> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<Data_of_video> getList() {
        return list;
    }
//
    public RV_video_adapter() {
    }
    public RV_video_adapter(List<Data_of_video> list) {
        this.list = list;
    }
    public RV_video_adapter(Context c , List<Data_of_video> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_video pp = list.get(position);
        holder.bind(pp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        Button btn;
        Data_of_video p;

        public RV_Holder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.vv_btn_name);
        }
        public void bind(Data_of_video model) {
            p = model;

            btn.setText(p.getVideoName());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //then open the video page ...
                    listener.click(p);
                }
            });

        }
    }
}