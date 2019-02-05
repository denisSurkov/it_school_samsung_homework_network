package com.example.denis.networkpractice2.controllers.http_task;

import android.os.AsyncTask;

import com.example.denis.networkpractice2.controllers.AsyncResponse;
import com.example.denis.networkpractice2.models.api.JSONPlaceholderAPI;
import com.example.denis.networkpractice2.models.entities.User;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UsersTask extends AsyncTask<Void, ArrayList<User>, ArrayList<User>> {


    public AsyncResponse delegate = null;

    public UsersTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected ArrayList<User> doInBackground(Void... voids) {
        JSONPlaceholderAPI api = new JSONPlaceholderAPI("https://jsonplaceholder.typicode.com/users/");

        try{
            ArrayList<User> users = api.getUsers();
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<User> userList) {
        delegate.processFinish(userList);
    }
}