package com.example.sample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.databinding.ItemUserBinding;
import com.example.sample.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.holder> {

    private List<User> userDetails;


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        User user = userDetails.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userDetails == null ? 0 : userDetails.size();
    }

    static class holder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public holder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(User user) {
            binding.setUser(user);
            binding.executePendingBindings();

        }
    }

    public void setData(List<User> userList) {
        userDetails = userList;
        notifyDataSetChanged();

    }

}
