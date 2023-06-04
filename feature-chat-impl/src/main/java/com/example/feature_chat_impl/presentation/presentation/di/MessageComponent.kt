package com.example.feature_chat_impl.presentation.presentation.di

import com.example.feature_chat_impl.presentation.presentation.fragment.ChatFragment
import com.example.feature_signs_impl.presentation.di.ZodiacComponent
import com.example.feature_signs_impl.presentation.di.ZodiacModule
import com.example.feature_signs_impl.presentation.fragments.FriendshipListFragment
import com.example.feature_signs_impl.presentation.fragments.LoveListFragment
import com.example.feature_signs_impl.presentation.fragments.ZodiacInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [MessageModule::class])
interface MessageComponent {

    fun injectChatFragment(fragment: ChatFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MessageComponent
    }
}