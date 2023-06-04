package com.example.feature_chat_api.interfaces

import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo

interface MessageRepository {

    suspend fun saveMessage(
        sender: String?,
        receiver: String?,
        text: String?,
    )

    suspend fun getBySender(sender: String?): ListMessage

    suspend fun getByReceiver(receiver: String?): ListMessage
}