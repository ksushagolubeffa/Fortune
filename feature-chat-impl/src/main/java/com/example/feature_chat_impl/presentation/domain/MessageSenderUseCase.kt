package com.example.feature_chat_impl.presentation.domain

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageSenderUseCase(
    private val messageRepository: MessageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        sender: String?
    ): ListMessage {
        return withContext(dispatcher) {
            messageRepository.getBySender(sender)
        }
    }
}