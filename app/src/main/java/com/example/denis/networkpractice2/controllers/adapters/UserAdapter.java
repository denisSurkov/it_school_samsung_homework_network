package com.example.denis.networkpractice2.controllers.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denis.networkpractice.R;
import com.example.denis.networkpractice2.activities.UserDetailActivity;
import com.example.denis.networkpractice2.models.entities.User;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    List<User> userList;

    public UserAdapter(List<User> userList){
        this.userList = userList;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH userVH, int position) {
        User user = userList.get(position);

        userVH.userEmail.setText(user.getEmail());
        userVH.userName.setText(user.getUsername());
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.user_list_item, parent, false);

        return new UserVH(view);
    }

    class UserVH extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView userEmail;
        private CardView card;

        private Context context;

        public UserVH(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            card = itemView.findViewById(R.id.userCard);
            userName = itemView.findViewById(R.id.userCardName);
            userEmail = itemView.findViewById(R.id.userCardEmail);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = userList.get(getAdapterPosition());
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra("userData", user);

                    context.startActivity(intent);
                }
            });
        }
    }

}
