package com.example.svetlana.finalexamsqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.svetlana.finalexamsqlite.AccomplishingTaskActivity;
import com.example.svetlana.finalexamsqlite.R;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList task_id, task_title, task_priority;

    public RViewAdapter(Activity activity, Context context, ArrayList task_id, ArrayList task_title, ArrayList task_priority){
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_priority = task_priority;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        holder.task_priority_txt.setText(String.valueOf(task_priority.get(position)));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AccomplishingTaskActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task_title.get(position)));
                intent.putExtra("priority", String.valueOf(task_priority.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_id_txt, task_title_txt, task_priority_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id_txt = itemView.findViewById(R.id.task_id_txt);
            task_title_txt = itemView.findViewById(R.id.task_title_txt);
            task_priority_txt = itemView.findViewById(R.id.task_priority_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
