package com.skcoder.intellifytest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{

    private Context context;
    private ArrayList<UserModel> list;

    public UsersAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        UserModel data = list.get(position);
        holder.name.setText(data.getName());
        holder.age.setText(data.getAge());
        holder.city.setText(data.getCity());
        holder.gender.setText(data.getGender());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("Name", list.get(position).getName());
                intent.putExtra("Age", list.get(position).getAge());
                intent.putExtra("Gender", list.get(position).getGender());
                intent.putExtra("City", list.get(position).getCity());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        TextView name, age, city, gender;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            city = itemView.findViewById(R.id.city);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}
