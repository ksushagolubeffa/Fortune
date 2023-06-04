package com.example.feature_chat_impl.presentation.domain

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddMessageUseCase(
    private val messageRepository: MessageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        sender: String?,
        receiver: String?,
        text: String?,
    ) {
        withContext(dispatcher) {
            messageRepository.saveMessage(sender, receiver, text)
        }
    }
}