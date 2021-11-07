package com.example.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textname;
        public TextView texttime;
        public CheckBox checkbox;
        public ImageButton deletebutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkbox = itemView.findViewById(R.id.checkBox);
            this.textname = itemView.findViewById(R.id.textView);
            this.texttime = itemView.findViewById(R.id.textView2);
            this.deletebutton = itemView.findViewById(R.id.imageButton);
        }
    }
    private List<ListItemBean> dataList;
    private OnClickListener onFinishListener;
    private OnClickListener onDeleteListener;

    public ListAdapter(List<ListItemBean> dataList){
        this.dataList=dataList;
    }
    public void setOnFinishListener(OnClickListener onFinishListener){
        this.onFinishListener=onFinishListener;
    }
    public void setDeleteListener(OnClickListener onDeleteListener)
    {
        this.onDeleteListener = onDeleteListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(itemView);
        initButtonListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItemBean item = dataList.get(position);
        holder.textname.setText(item.getContent());
        holder.texttime.setText(item.getTime());
        if (item.getStatus() == ListItemBean.FINISH)
        {
            holder.checkbox.setChecked(true);
        }
        else
        {
            holder.checkbox.setChecked((false));
        }
    }

    private void initButtonListener(final ViewHolder viewHolder) {

        viewHolder.checkbox.setOnClickListener(v -> {
            int pos=viewHolder.getAdapterPosition();
            onFinishListener.onClick(v,pos);
        });
        viewHolder.deletebutton.setOnClickListener(v -> {
            int pos=viewHolder.getAdapterPosition();
            onDeleteListener.onClick(v,pos);
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
