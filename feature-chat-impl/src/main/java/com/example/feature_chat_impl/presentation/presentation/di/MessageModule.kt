package com.example.feature_chat_impl.presentation.presentation.di

import android.os.Message
import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_impl.presentation.data.MessageRepositoryImpl
import com.example.feature_chat_impl.presentation.di.MessageAPI
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageReceiverUseCase
import com.example.feature_chat_impl.presentation.domain.MessageSenderUseCase
import com.example.feature_signs_impl.data.ZodiacRepositoryImpl
import com.example.feature_signs_impl.di.ZodiacAPI
import com.example.feature_signs_impl.domain.ZodiacUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MessageModule {

    @Provides
    fun provideZodiacRequests (
        retrofit: Retrofit
    ): MessageAPI = retrofit.create(MessageAPI ::class.java)

    @Provides
    fun provideMessageSenderUseCase(
        repository: MessageRepository
    ): MessageSenderUseCase = MessageSenderUseCase(repository)

    @Provides
    fun provideMessageReceiverUseCase(
        repository: MessageRepository
    ): MessageReceiverUseCase = MessageReceiverUseCase(repository)

    @Provides
    fun provideAddMessageUseCase(
        repository: MessageRepository
    ): AddMessageUseCase = AddMessageUseCase(repository)

    @Provides
    fun provideMessageRepository(
        messageApi : MessageAPI
    ): MessageRepository = MessageRepositoryImpl(messageApi)
}
