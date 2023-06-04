package com.example.feature_chat_impl.presentation.data

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_impl.presentation.di.MessageAPI
import com.example.feature_chat_impl.presentation.di.MessageResponse

class MessageRepositoryImpl(
    private val api: MessageAPI
)
    : MessageRepository {
    override suspend fun saveMessage(sender: String?, receiver: String?, text: String?) {
        val messageResponse = MessageResponse(
            sender = sender,
            receiver = receiver,
            text = text
        )
        api.saveComment(messageResponse)
    }

    override suspend fun getBySender(sender: String?): ListMessage {
        val value = ArrayList<MessageInfo>()
        val response = api.getBySender(sender)
        response.forEach {
            value.add(
                MessageInfo(
                    sender = it.sender,
                    receiver = it.receiver,
                    text = it.text
                )
            )
        }
        return ListMessage(value)
    }

    override suspend fun getByReceiver(receiver: String?): ListMessage {
        val value = ArrayList<MessageInfo>()
        val response = api.getByReceiver(receiver)
        response.forEach {
            value.add(
                MessageInfo(
                    sender = it.sender,
                    receiver = it.receiver,
                    text = it.text
                )
            )
        }
        return ListMessage(value)
    }
}