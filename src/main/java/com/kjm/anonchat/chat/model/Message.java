package com.kjm.anonchat.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    private String id;
    private String chatID;
    private String senderId;
    private String recipientId;
    private String content;
    private Status status;
    private String senderName;
    private String recipientName;
    private Date timestamp;
}
