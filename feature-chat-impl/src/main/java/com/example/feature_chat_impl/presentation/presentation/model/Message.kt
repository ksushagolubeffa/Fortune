package com.example.feature_chat_impl.presentation.presentation.model

class Message {
    var message: String? = null
    var senderEmail: String?= null

    constructor(){}

    constructor(message: String?, senderEmail: String?){
        this.message = message
        this.senderEmail = senderEmail
    }
}