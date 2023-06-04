package com.example.feature_chat_impl.presentation.di

import com.example.feature_chat_api.model.MessageInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageAPI {

    @GET("/message/getBySender/{sender}")
    suspend fun getBySender(
        @Path("sender") sender: String?
    ): List<MessageResponse>

    @GET("/message/getByReceiver/{receiver}")
    suspend fun getByReceiver(
        @Path("receiver") receiver: String?
    ): List<MessageResponse>

    @POST("/message/save")
    suspend fun saveComment(@Body messageData: MessageResponse): Call<MessageInfo>
}