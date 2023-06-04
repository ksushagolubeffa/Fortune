package com.example.feature_chat_impl.presentation.presentation.rv_chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_sign_impl.R

class ReceiveHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
    val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)

}