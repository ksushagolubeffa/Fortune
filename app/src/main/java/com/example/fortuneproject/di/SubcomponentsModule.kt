package com.example.fortuneproject.di

import com.example.feature_chat_impl.presentation.presentation.di.MessageComponent
import com.example.feature_chat_impl.presentation.presentation.di.UserZodiacComponent
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponent
import com.example.feature_signs_impl.presentation.di.ZodiacComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import dagger.Module

@Module(subcomponents = [RegistrationComponent::class, MainScreenComponent::class, ProfileScreenComponent::class, ZodiacComponent::class, MessageComponent::class, UserZodiacComponent::class])
class SubcomponentsModule {
}
