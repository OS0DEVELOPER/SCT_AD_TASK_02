package com.example.to_dolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAddTask;
    private Button buttonClearSelectedTasks; // Clear Selected Button
    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonClearSelectedTasks = findViewById(R.id.buttonClearSelectedTasks); // Find Clear Selected Button
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        // Initialize Task List and Adapter
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);

        // Set up RecyclerView
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        // Add Task Button Listener
        buttonAddTask.setOnClickListener(v -> {
            String taskName = editTextTask.getText().toString().trim();
            if (!taskName.isEmpty()) {
                taskList.add(new Task(taskName));
                taskAdapter.notifyItemInserted(taskList.size() - 1);
                editTextTask.setText(""); // Clear input field
            }
        });

        // Clear Selected Tasks Button Listener
        buttonClearSelectedTasks.setOnClickListener(v -> {
            Iterator<Task> iterator = taskList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().isSelected()) {
                    iterator.remove(); // Remove selected tasks
                }
            }
            taskAdapter.notifyDataSetChanged(); // Notify adapter of data change
        });
    }
}
