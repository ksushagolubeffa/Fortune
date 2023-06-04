package com.example.feature_chat_impl.presentation.presentation.di

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.interfaces.UserZodiacRepository
import com.example.feature_chat_impl.presentation.data.MessageRepositoryImpl
import com.example.feature_chat_impl.presentation.data.UserZodiacRepositoryImpl
import com.example.feature_chat_impl.presentation.di.MessageAPI
import com.example.feature_chat_impl.presentation.di.UserZodiacAPI
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.UserZodiacUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserZodiacModule {

    @Provides
    fun provideZodiacRequests (
        retrofit: Retrofit
    ): UserZodiacAPI = retrofit.create(UserZodiacAPI ::class.java)

    @Provides
    fun provideUserZodiacUseCase(
        repository: UserZodiacRepository
    ): UserZodiacUseCase = UserZodiacUseCase(repository)

    @Provides
    fun provideUserZodiacRepository(
        zodiacApi : UserZodiacAPI
    ): UserZodiacRepository = UserZodiacRepositoryImpl(zodiacApi)
}