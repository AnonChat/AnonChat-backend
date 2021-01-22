package com.kjm.anonchat.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
@Builder
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
