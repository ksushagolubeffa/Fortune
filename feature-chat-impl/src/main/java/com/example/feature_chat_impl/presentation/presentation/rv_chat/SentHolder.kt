package com.example.feature_chat_impl.presentation.presentation.rv_chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_sign_impl.R

class SentHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
}