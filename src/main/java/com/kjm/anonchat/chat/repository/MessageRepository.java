package com.kjm.anonchat.chat.repository;

import com.kjm.anonchat.chat.model.Message;
import com.kjm.anonchat.chat.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, Status status);

    List<Message> findByChatID(String id);
}
