package com.example.foucson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foucson.db.Information;

import org.w3c.dom.Text;

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
        CharSequence date = android.text.format.DateFormat.format(" yyyy.MM.dd  hh:mm", infor.getTime());
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
