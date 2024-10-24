package com.example.mediscribe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> chatMessageList;

    public ChatAdapter(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessageList.get(position);

        if (chatMessage.isUser()) {
            holder.userMessageTextView.setVisibility(View.VISIBLE);
            holder.aiMessageTextView.setVisibility(View.GONE);
            holder.userMessageTextView.setText(chatMessage.getMessage());
        } else {
            holder.aiMessageTextView.setVisibility(View.VISIBLE);
            holder.userMessageTextView.setVisibility(View.GONE);
            holder.aiMessageTextView.setText(chatMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userMessageTextView, aiMessageTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userMessageTextView = itemView.findViewById(R.id.userMessageTextView);
            aiMessageTextView = itemView.findViewById(R.id.aiMessageTextView);
        }
    }
}

