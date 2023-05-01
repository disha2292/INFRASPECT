package com.example.infraspect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    public MyAdapter(Context context, ArrayList<ProjectModel> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<ProjectModel> list;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.allproject, parent, false);
        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProjectModel model = list.get(position);
        holder.projectName.setText(model.getProjectName());
        holder.address.setText(model.getAddress());
        holder.startDate.setText(model.getStartDate());
        holder.endDate.setText(model.getEndDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AdminParticularActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView projectName, address, startDate, endDate;
//        private Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.pname);
            address = itemView.findViewById(R.id.paddress);
            startDate = itemView.findViewById(R.id.sdate);
            endDate = itemView.findViewById(R.id.edate);
//            itemView.setOnClickListener(this);





        }
    }
}