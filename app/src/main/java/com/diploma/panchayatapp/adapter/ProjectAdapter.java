package com.diploma.panchayatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.activities.UserHomePage;
import com.diploma.panchayatapp.model.ProjectModel;
import com.diploma.panchayatapp.utils.constants;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_pdesc.setText(projectModelList.get(position).getProjectDesc());
        holder.txt_pname.setText(projectModelList.get(position).getProjectname());
        holder.txt_pduration.setText("Duration: "+String.valueOf(projectModelList.get(position).getDuration())+" Month(s)");
        holder.txt_pstartDate.setText("Start Date: "+projectModelList.get(position).getStartDate());
        holder.txt_ppname.setText("Panchayat Name: "+projectModelList.get(position).getPanchayatName());
        if (!projectModelList.get(position).getLink().equals("")) {
            SpannableString content = new SpannableString("Link: " + projectModelList.get(position).getLink());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            holder.txt_link.setText(content);
            holder.txt_link.setTextColor(context.getColor(R.color.link));
        } else {
            holder.txt_link.setText("Link: " + "N/A");
            holder.txt_link.setTextColor(context.getColor(R.color.textColor));
        }

        holder.txt_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!projectModelList.get(position).getLink().equals("")) {
                    if (constants.isNetworkAvilable(context)) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(projectModelList.get(position).getLink()));
                        context.startActivity(browserIntent);
                    } else {
                        Toast.makeText(context, "No network available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return projectModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_pname,txt_pdesc,txt_pduration,txt_pstartDate,txt_ppname,txt_link;
        public ViewHolder(@NonNull View view) {
            super(view);
            txt_pname=view.findViewById(R.id.txt_pname);
            txt_pdesc=view.findViewById(R.id.txt_pdesc);
            txt_pduration=view.findViewById(R.id.txt_pduration);
            txt_pstartDate=view.findViewById(R.id.txt_pstartDate);
            txt_ppname=view.findViewById(R.id.txt_ppname);
            txt_link=view.findViewById(R.id.txt_link);

        }
    }
}
