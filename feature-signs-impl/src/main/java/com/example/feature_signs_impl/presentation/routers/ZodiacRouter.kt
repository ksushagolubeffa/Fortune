package com.example.feature_signs_impl.presentation.routers

import android.content.Context
import android.os.Bundle
import com.example.feature_signs_impl.presentation.fragments.ZodiacInfoFragment

interface ZodiacRouter {

    fun openStart(context: Context)

    fun openZodiacInfoFragment(bundle: Bundle)

    fun openChat(bundle: Bundle)
}