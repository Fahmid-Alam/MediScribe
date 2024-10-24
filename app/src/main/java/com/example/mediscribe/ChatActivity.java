package com.example.mediscribe;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;
    private EditText messageEditText;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);

        // Initialize the RecyclerView and other views
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        // Initialize the list of chat messages
        chatMessages = new ArrayList<>();

        chatMessages.add(new ChatMessage("Hello!", true));
        chatMessages.add(new ChatMessage("I need help with my symptoms.", true));

        // AI responses
        chatMessages.add(new ChatMessage("Hello! How can I assist you today?", false));
        chatMessages.add(new ChatMessage("Could you describe your symptoms?", false));

        // Set up the RecyclerView with the ChatAdapter
        chatAdapter = new ChatAdapter(chatMessages);
        chatRecyclerView.setAdapter(chatAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up Send Button click listener
        sendButton.setOnClickListener(v -> {
            String userMessage = messageEditText.getText().toString().trim();
            if (!userMessage.isEmpty()) {
                // Add the user's message to the chat
                addMessage(userMessage, true);

                // Clear the input field
                messageEditText.setText("");

                // Simulate AI response (mock AI interaction)
                respondToUserMessage(userMessage);
            }
        });
    }

    // Method to add a message (either from the user or AI) to the chat
    private void addMessage(String message, boolean isUser) {
        chatMessages.add(new ChatMessage(message, isUser));
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        chatRecyclerView.scrollToPosition(chatMessages.size() - 1);  // Scroll to the last message
    }

    // Method to simulate AI response (for now we are mocking the AI response)
    private void respondToUserMessage(String userMessage) {
        new Handler().postDelayed(() -> {
            // Generate a mock AI response (later you'll replace this with real AI integration)
            String aiResponse = "AI Response to: " + userMessage;
            addMessage(aiResponse, false);  // Add AI response to the chat
        }, 1000);  // Delay to simulate processing time
    }
}
