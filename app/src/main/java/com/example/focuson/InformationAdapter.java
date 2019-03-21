package com.example.focuson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.focuson.db.Information;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationViewHolder> {
    private Context mContext;

    private List<Information> inforList;

    public InformationAdapter(List<Information> list)
    {
        inforList = list;
    }

    @NonNull
    @Override
    public InformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null)
        {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_information, parent, false);
        InformationViewHolder holder = new InformationViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return inforList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull InformationViewHolder holder, int position) {
        Information infor = inforList.get(position);
        holder.contentView.setText(infor.getContent());
        CharSequence date = android.text.format.DateFormat.format(" yyyy.MM.dd", infor.getTime());
        holder.timeView.setText(date);
    }

    public class InformationViewHolder extends RecyclerView.ViewHolder {
        private TextView contentView;
        private TextView timeView;

        public InformationViewHolder(View itemView) {
            super(itemView);
            contentView = itemView.findViewById(R.id.content_view);
            timeView = itemView.findViewById(R.id.time_view);
        }
    }
}
