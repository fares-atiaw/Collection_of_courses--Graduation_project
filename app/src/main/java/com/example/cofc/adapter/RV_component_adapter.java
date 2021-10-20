package com.example.cofc.adapter;

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
import com.example.cofc.model.Data_of_component;
import com.example.cofc.model.Data_of_video;

import java.util.ArrayList;
import java.util.List;

public class RV_component_adapter extends RecyclerView.Adapter<RV_component_adapter.RV_Holder> {

    private List<Data_of_component> list = new ArrayList<>();
    VideoItem_onClick listener;
    private LinearLayout visibleLayout = null;

    public void setList(List<Data_of_component> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public VideoItem_onClick getListener() { return listener; }
    public void setListener(VideoItem_onClick listener) { this.listener = listener; }

    public List<Data_of_component> getList() {
        return list;
    }

    public RV_component_adapter(List<Data_of_component> list) {
        this.list = list;
    }

    @NonNull
    @Override   //Works for the first apperant items according to each mobile's screen only
    public RV_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RV_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_component, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Holder holder, int position) {
        Data_of_component pp = list.get(position);
        holder.bind(pp);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visibleLayout != null)
                    visibleLayout.setVisibility(View.GONE);

                holder.l.setVisibility(View.VISIBLE);
                visibleLayout = holder.l;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // The holder of the recycler_view list
    public class RV_Holder extends RecyclerView.ViewHolder {
        Button btn;
        RecyclerView rv;
        LinearLayout l;
        RV_video_adapter adapter;

        Data_of_component p;
        public RV_Holder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.vc_btn_name);
            rv = itemView.findViewById(R.id.vc_rv_video);
            l = itemView.findViewById(R.id.vc_layout_video);
        }

        public void bind(Data_of_component model) {
            p = model;

            btn.setText(p.getComponentName());

            adapter = new RV_video_adapter(l.getContext(), p.getVideo());
            adapter.setListener(new VideoItem_onClick() {
                @Override
                public void click(Data_of_video data) {
                    listener.click(data);
                }
            });

            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
            rv.setHasFixedSize(true);
            rv.setItemAnimator(new DefaultItemAnimator());
        }
    }
}