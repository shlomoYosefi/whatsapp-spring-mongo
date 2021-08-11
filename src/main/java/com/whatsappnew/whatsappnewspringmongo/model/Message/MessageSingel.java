package com.whatsappnew.whatsappnewspringmongo.model.Message;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.HashMap;


@Document
@NoArgsConstructor
public class MessageSingel extends Message {


    @Id
    private String id;
    private String receiverId;
    private String receiverName;
    private String senderName;



    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public MessageSingel(String text, Date dateTime, String senderId, File attachment, String attachmentType, String receiverId) {
        super(text, dateTime, senderId, attachment, attachmentType);
        this.receiverId = receiverId;
    }

    public MessageSingel(String text, Date dateTime, String senderId, int originalMessageId, String originalMessageText , String receiverId) {
        super(text, dateTime, senderId, originalMessageId, originalMessageText);
        this.receiverId = receiverId;

    }

    public MessageSingel(String text, Date dateTime, String senderId, String receiverId) {
        super(text, dateTime, senderId);
        this.receiverId = receiverId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getReceiverId() {
        return receiverId;
    }



    @Override
    public String toString() {
        return "\"" + "message" + this.getId()+"\"" +":{" +
                "\"" + "text" + "\"" + ":"+  "\"" +this.getText() + "\","+
                "\"" + "date" +"\"" +":"+ "\"" + this.getDateTime() + "\","+
                "\"" + "dateString" +"\"" +":"+ "\"" + this.getDateString() + "\","+
                "\"" + "timeString" +"\"" +":"+ "\"" + this.getTimeString() + "\","+
                "\"" + "reciverName" +"\"" +":"+ "\"" + this.getReceiverName() + "\","+
                "\"" + "senderName" +"\"" +":"+ "\"" + this.getSenderName() + "\","+
                "\"" + "senderId" +  "\""+":" +"\""+ getSenderId() + "\","+
                "\"" + "id" + "\""+":" + "\"" +id + "\","+
                "\"" + "receiverId" + "\""+":" + "\"" + receiverId + "\""+
                "}";
        }

    public  static class DBMessage {
        public HashMap<String, MessageSingel> map;
    }
}
