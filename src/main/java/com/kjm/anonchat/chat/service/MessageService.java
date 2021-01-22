package com.kjm.anonchat.chat.service;

import com.kjm.anonchat.chat.exception.ResourceNotFoundException;
import com.kjm.anonchat.chat.model.Message;
import com.kjm.anonchat.chat.model.Status;
import com.kjm.anonchat.chat.repository.MessageRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private RoomService roomService;
    private MongoOperations mongoOperations;

    public MessageService(MessageRepository messageRepository,
                          RoomService roomService,
                          MongoOperations mongoOperations) {
        this.messageRepository = messageRepository;
        this.roomService = roomService;
        this.mongoOperations = mongoOperations;
    }

    public Message save(Message message) {
        message.setStatus(Status.RECEIVED);
        messageRepository.save(message);
        return message;
    }

    public Message findById(String id) {
        return messageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(Status.DELIVERED);
                    return messageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    public List<Message> findMessages(String senderId, String recipientId) {
        Optional<String> chatId = roomService.getChatId(senderId, recipientId, false);

        List<Message> messages = chatId.map(id -> messageRepository.findByChatID(id)).orElse(new ArrayList<>());

       if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, Status.DELIVERED);
        }

        return messages;
    }

    public void updateStatuses(String senderId, String recipientId, Status status) {
        Query query = new Query(Criteria
                    .where("senderId").is(senderId)
                    .and("recipientId").is(recipientId));

        Update update = Update.update("status",status);
        mongoOperations.updateMulti(query, update, Message.class);
    }

    public long countAllNewMessages(String senderId, String recipientId) {
        return messageRepository.countBySenderIdAndRecipientIdAndStatus(senderId,recipientId, Status.RECEIVED);
    }



}
