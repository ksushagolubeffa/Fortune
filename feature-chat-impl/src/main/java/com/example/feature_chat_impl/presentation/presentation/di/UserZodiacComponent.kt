package com.example.feature_chat_impl.presentation.presentation.di

import com.example.feature_signs_impl.presentation.di.ZodiacComponent
import dagger.Subcomponent

@Subcomponent(modules = [UserZodiacModule::class])
interface UserZodiacComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): UserZodiacComponent
    }
}