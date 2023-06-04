package com.example.feature_chat_impl.presentation.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_api.model.UserZodiacInfo
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageReceiverUseCase
import com.example.feature_chat_impl.presentation.domain.MessageSenderUseCase
import com.example.feature_chat_impl.presentation.domain.UserZodiacUseCase
import com.example.feature_chat_impl.presentation.presentation.di.MessageComponentProvider
import com.example.feature_chat_impl.presentation.presentation.rv_chat.MessageAdapter
import com.example.feature_chat_impl.presentation.presentation.viewmodel.MessageViewModel
import com.example.feature_chat_impl.presentation.presentation.viewmodel.UserZodiacViewModel
import com.example.feature_sign_impl.R
import com.example.feature_sign_impl.databinding.FragmentChatBinding
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatFragment: Fragment(R.layout.fragment_chat) {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private var binding: FragmentChatBinding? = null
    private var email: String? = ""
    private var user: UserZodiacInfo?=null

    @Inject
    lateinit var router: ZodiacRouter
    @Inject
    lateinit var messageSenderUseCase: MessageSenderUseCase
    @Inject
    lateinit var messageReceiverUseCase: MessageReceiverUseCase
    @Inject
    lateinit var addMessageUseCase: AddMessageUseCase
    @Inject
    lateinit var userZodiacUseCase: UserZodiacUseCase

    private val userViewModel: UserZodiacViewModel by viewModels {
        UserZodiacViewModel.provideFactory(
            router,
            userZodiacUseCase
        )
    }

    private val messageViewModel: MessageViewModel by viewModels {
        MessageViewModel.provideFactory(
            router,
            messageSenderUseCase,
            messageReceiverUseCase,
            addMessageUseCase
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var messageComponent = (requireActivity().application as MessageComponentProvider)
            .provideMessageComponent()
        messageComponent.injectChatFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initObservers()
        email = arguments?.getString("name")
        val zodiac = arguments?.getString("zodiac")
        getPartner(email!!, zodiac!!)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChatBinding.bind(view)
        chatRecyclerView = binding?.chatRecyclerView!!
        messageBox = binding?.messageBox!!
        sendButton = binding?.btn!!
        rvCreator(email!!, user?.email!!)
        sendButton.setOnClickListener {
            messageViewModel.addMessage(email, user?.email, messageBox.text.toString())
            messageAdapter.notifyDataSetChanged()
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initObservers(){
        userViewModel.user.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { getUser(it)
                },
                onFailure = { Log.e("Chat fragment", "this is user")})
        }

        messageViewModel.receiverMessages.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = {
                        listModel ->
                    chatRecyclerView?.run {
                        lifecycleScope.launch {
                            adapter = MessageAdapter(context, listModel.list)
                        }
                    }
                },
                onFailure = { Log.e("Chat fragment", "receiver")})
        }

        messageViewModel.senderMessages.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = {
                        listModel ->
                    chatRecyclerView?.run {
                        lifecycleScope.launch {
                            adapter = MessageAdapter(context, listModel.list)
                        }
                    }
                },
                onFailure = { Log.e("Chat fragment", "sender")})
        }
    }

    private fun getPartner(zodiac: String, email: String){
        userViewModel.getUser(zodiac, email)
    }

    private fun getUser(gotUser: UserZodiacInfo){
        user = gotUser
    }

    private fun rvCreator(sender: String, receiver: String){
        messageViewModel.getBySender(sender)
        messageViewModel.getByReceiver(receiver)
    }
}