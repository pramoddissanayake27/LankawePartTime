package com.example.pramod_shash.lankaweparttime;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class JobShowAdapter extends RecyclerView.Adapter<JobShowAdapter.ViewHolder> {

    private List<JobCreatingWithQualifications> listItems;
    private Context context;


    public JobShowAdapter(List<JobCreatingWithQualifications> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JobCreatingWithQualifications listItem = listItems.get(position);

        holder.jobInfo.setText(listItem.getJobName()+"\n"+listItem.getLocation());
        holder.jobPayment.setText(listItem.getPaymentPerEach()+"/=");

        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JobMoreDetailsActivity.class);
                intent.putExtra("JobDetails",listItem);
                context.startActivity(intent);
                //context.startActivity(new Intent(context,JobMoreDetailsActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView jobInfo;
        public Button btnSeeDetails;
        public TextView jobPayment;
        public ConstraintLayout clickDetect;

        public ViewHolder(View itemView) {
            super(itemView);

            jobInfo = itemView.findViewById(R.id.jobInfo);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
            jobPayment = itemView.findViewById(R.id.tvJobPayment);
            clickDetect = itemView.findViewById(R.id.clickDetect);

        }
    }



}
