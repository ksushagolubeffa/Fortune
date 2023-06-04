package com.example.feature_chat_impl.presentation.data

import com.example.feature_chat_api.interfaces.UserZodiacRepository
import com.example.feature_chat_api.model.UserZodiacInfo
import com.example.feature_chat_impl.presentation.di.UserZodiacAPI

class UserZodiacRepositoryImpl(
    private val api: UserZodiacAPI
) : UserZodiacRepository {

    override suspend fun getUserByZodiac(zodiac: String, email: String): UserZodiacInfo {
        val user = api.getUserByZodiac(zodiac, email)
        return UserZodiacInfo(
            username = user.username,
            email = user.email,
            birth = user.birth,
            male = user.male
        )
    }
}