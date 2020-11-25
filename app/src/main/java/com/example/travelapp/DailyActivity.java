package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyActivity extends AppCompatActivity {

    Button btnBack, btnAdd;
    EditText etAddTask;
    TextView tvDaily;
    TaskDBHelper db_helper_instance;
    List<Task> task_collection;
    TaskAdapter ta_intance;
    RecyclerView rvDaily;
    TaskAdapter.TaskLongPress tatlp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        btnAdd=findViewById(R.id.btnAdd);
        btnBack=findViewById(R.id.btnBack);
        etAddTask=findViewById(R.id.etAddTask);
        tvDaily=findViewById(R.id.tvDaily);
        rvDaily=findViewById(R.id.rvDaily);

        //formating date: long -> date -> string
        final long selected_date= getIntent().getExtras().getLong(ScheduleFragment.SELECTED_DATE);
        Date in_date= new Date(selected_date);
        Format df = new SimpleDateFormat("MM-dd-yyyy");
        String in_string= df.format(in_date);
        tvDaily.setText(in_string);


        db_helper_instance=new TaskDBHelper(DailyActivity.this);
        task_collection= new ArrayList<>();
        //task_collection=db_helper_instance.getAllItem();
        task_collection=db_helper_instance.getTaskByDate(selected_date);
        Log.e("DailyActivity", "size: "+ task_collection.size());
        for(int i=0;i<task_collection.size();i++){
            Log.i("DailyActivity", "task date::::: "+ task_collection.get(i).getDate());
        }
        //task_collection=db_helper_instance.getTaskByDate(selected_date);

        tatlp= new TaskAdapter.TaskLongPress() {
            @Override
            public void longPressPosition(int pos) {
                //remove task by id from data base.
                db_helper_instance.removeTask(task_collection.get(pos));
                //todo: reflect changes on app
                task_collection.clear();
                task_collection=db_helper_instance.getAllItem();
                //task_collection=db_helper_instance.getTaskByDate(selected_date);
                ta_intance.notifyDataSetChanged();
            }
        };


        ta_intance=new TaskAdapter(task_collection,DailyActivity.this,tatlp);
        rvDaily.setAdapter(ta_intance);
        rvDaily.setLayoutManager(new LinearLayoutManager(DailyActivity.this));






        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //going back to main calendar
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding or writing new task to SQL Database.
                //1) create the task
                //2) ask db_helper instance to add the task item into the db.
                String taskName=etAddTask.getText().toString();
                Task add_task;

                try {
                    add_task = new Task(taskName, selected_date, -1);
                    //Toast.makeText(DailyActivity.this, add_task.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    //Toast.makeText(DailyActivity.this, "Error creating new task", Toast.LENGTH_SHORT).show();
                    add_task= new Task("error",selected_date,-1);
                }

                //create instance and the database.
                db_helper_instance=new TaskDBHelper(DailyActivity.this);
                boolean success = db_helper_instance.addTask(add_task);
                Toast.makeText(DailyActivity.this, "added---" + success, Toast.LENGTH_SHORT).show();

            }
        });

    }
}