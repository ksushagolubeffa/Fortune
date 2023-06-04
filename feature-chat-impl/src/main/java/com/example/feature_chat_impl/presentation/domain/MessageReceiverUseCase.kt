package com.example.feature_chat_impl.presentation.domain

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageReceiverUseCase(
    private val messageRepository: MessageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        receiver: String?
    ):ListMessage {
        return withContext(dispatcher) {
            messageRepository.getByReceiver(receiver)
        }
    }
}