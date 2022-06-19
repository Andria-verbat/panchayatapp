package com.diploma.panchayatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.model.ProjectModel;

import java.util.List;

/**
 * Created by Andria on 4/23/2022.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    Context context;
    List<ProjectModel> projectModelList;

    public ProjectAdapter(Context context, List<ProjectModel> projectModelList) {
        this.context = context;
        this.projectModelList = projectModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projectitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_pdesc.setText(projectModelList.get(position).getProjectDesc());
        holder.txt_pname.setText(projectModelList.get(position).getProjectname());
        holder.txt_pduration.setText("Duration: "+String.valueOf(projectModelList.get(position).getDuration())+" Month(s)");
        holder.txt_pstartDate.setText("Start Date: "+projectModelList.get(position).getStartDate());
        holder.txt_ppname.setText("Panchayat Name: "+projectModelList.get(position).getPanchayatName());

    }

    @Override
    public int getItemCount() {
        return projectModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_pname,txt_pdesc,txt_pduration,txt_pstartDate,txt_ppname;
        public ViewHolder(@NonNull View view) {
            super(view);
            txt_pname=view.findViewById(R.id.txt_pname);
            txt_pdesc=view.findViewById(R.id.txt_pdesc);
            txt_pduration=view.findViewById(R.id.txt_pduration);
            txt_pstartDate=view.findViewById(R.id.txt_pstartDate);
            txt_ppname=view.findViewById(R.id.txt_ppname);

        }
    }
}
