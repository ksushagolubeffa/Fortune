package com.example.feature_chat_impl.presentation.presentation.rv_chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_sign_impl.R

class MessageAdapter(val context: Context, val messageList: List<MessageInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==1){
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            ReceiveHolder(view)
        } else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            SentHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(true){ //here is check is current user email equals current message email (1:50:00)
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentMessage = messageList[position]

        if (holder.javaClass == SentHolder::class.java) {
            val viewHolder = holder as SentHolder
            holder.sentMessage.text = currentMessage.text
        } else {
            val viewHolder = holder as ReceiveHolder
            holder.receiveMessage.text = currentMessage.text
        }
    }
}