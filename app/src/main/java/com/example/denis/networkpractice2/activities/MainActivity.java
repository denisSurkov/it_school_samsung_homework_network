package com.example.denis.networkpractice2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.denis.networkpractice.R;
import com.example.denis.networkpractice2.controllers.AsyncResponse;
import com.example.denis.networkpractice2.controllers.adapters.UserAdapter;
import com.example.denis.networkpractice2.controllers.http_task.UsersTask;
import com.example.denis.networkpractice2.models.entities.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    public void processFinish(ArrayList<User> users) {
        RecyclerView recyclerView = findViewById(R.id.usersRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new UserAdapter(users));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_users);

        UsersTask usersTask = new UsersTask(this);
        usersTask.execute();
    }
}
