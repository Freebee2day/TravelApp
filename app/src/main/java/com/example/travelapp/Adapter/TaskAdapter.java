package com.example.travelapp.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.Classes.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHoler> {
    List<Task> collection;
    Context context;
    TaskLongPress tlp;

    public interface TaskLongPress{
         void longPressPosition(int pos);
    }

    public TaskAdapter(List<Task> collection, Context context, TaskLongPress tlp) {
        this.collection = collection;
        this.context = context;
        this.tlp=tlp;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View task_view= LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        return new ViewHoler(task_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Task x = collection.get(position);
        holder.bind(x);
    }

    @Override
    public int getItemCount() {
        return collection.size();
    }





    public class ViewHoler extends RecyclerView.ViewHolder {

        RelativeLayout rlTaskItem;
        CheckBox cbTask;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            rlTaskItem=itemView.findViewById(R.id.rlTaskItem);
            cbTask=itemView.findViewById(R.id.cbTask);
        }

        public void bind(final Task x) {
            //display task name
            cbTask.setText(x.getTaskName());

            //check the box the cross out completed task
            cbTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        cbTask.setPaintFlags(cbTask.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                        x.set
                    }else{
                        cbTask.setPaintFlags(cbTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });


            //long press to delete task
            cbTask.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    tlp.longPressPosition(getAdapterPosition());
                    return true;
                }
            });

        }

    }
}
