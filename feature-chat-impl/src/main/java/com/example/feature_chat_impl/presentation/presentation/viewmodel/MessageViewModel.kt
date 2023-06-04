package com.example.feature_chat_impl.presentation.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_api.model.UserZodiacInfo
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageReceiverUseCase
import com.example.feature_chat_impl.presentation.domain.MessageSenderUseCase
import com.example.feature_chat_impl.presentation.domain.UserZodiacUseCase
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch

class MessageViewModel(
    private val router: ZodiacRouter,
    private val messageSenderUseCase: MessageSenderUseCase,
    private val messageReceiverUseCase: MessageReceiverUseCase,
    private val addMessageUseCase: AddMessageUseCase
):  ViewModel() {

    private val _message = MutableLiveData<Result<MessageInfo>>()
    val message: LiveData<Result<MessageInfo>>
        get() = _message

    private val _senderMessages = MutableLiveData<Result<ListMessage>>()
    val senderMessages: LiveData<Result<ListMessage>>
        get() = _senderMessages

    private val _receiverMessages = MutableLiveData<Result<ListMessage>>()
    val receiverMessages: LiveData<Result<ListMessage>>
        get() = _receiverMessages


    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun addMessage(sender: String?, receiver: String?, text: String?){
        viewModelScope.launch {
            try {
                addMessageUseCase(sender, receiver, text)
            } catch (ex: Exception) {
                _message.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

    fun getBySender(sender: String){
        viewModelScope.launch {
            try {
                val senderMessages = messageSenderUseCase(sender)
                _senderMessages.value = Result.success(senderMessages)
            } catch (ex: Exception) {
                _message.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

    fun getByReceiver(receiver: String){
        viewModelScope.launch {
            try {
                val receiverMessages = messageReceiverUseCase(receiver)
                _receiverMessages.value = Result.success(receiverMessages)
            } catch (ex: Exception) {
                _message.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

    companion object{
        fun provideFactory(
            router: ZodiacRouter,
            messageSenderUseCase: MessageSenderUseCase,
            messageReceiverUseCase: MessageReceiverUseCase,
            addMessageUseCase: AddMessageUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MessageViewModel(
                    router,
                    messageSenderUseCase,
                    messageReceiverUseCase,
                    addMessageUseCase
                )
            }
        }
    }

}