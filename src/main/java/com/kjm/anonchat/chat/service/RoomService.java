package com.kjm.anonchat.chat.service;

import com.kjm.anonchat.chat.model.Room;
import com.kjm.anonchat.chat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExists) {
        return roomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(Room::getChatId)
                .or(() -> {
                    if(!createIfNotExists) {
                        return Optional.empty();
                    }
                    var chatId = String.format("%s_%s", senderId, recipientId);

                    Room senderRecipient = Room.builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    Room recipientSender = Room.builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();

                    roomRepository.save(senderRecipient);
                    roomRepository.save(recipientSender);
                    return Optional.of(chatId);
                });
    }
}
