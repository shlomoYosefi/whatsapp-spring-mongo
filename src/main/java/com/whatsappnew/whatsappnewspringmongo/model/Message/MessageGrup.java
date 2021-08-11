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
public class MessageGrup extends Message {
    @Id
    private String id;
    private String nameGrup;
    private String nameSender;


        public void setNameGrup(String nameGrup) {
        this.nameGrup = nameGrup;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public MessageGrup(String text, Date dateTime, String senderId, File attachment, String attachmentType, String nameGrup) {
        super(text, dateTime, senderId, attachment, attachmentType);
        this.nameGrup = nameGrup;
    }

    public MessageGrup(String text, Date dateTime, String senderId, int originalMessageId, String originalMessageText , String nameGrup) {
        super(text, dateTime, senderId, originalMessageId, originalMessageText);
        this.nameGrup = nameGrup;
    }

    public MessageGrup(String text, Date dateTime, String senderId, String nameGrup) {
        super(text, dateTime, senderId);
        this.nameGrup = nameGrup;
    }


    public String getId() {
        return id;
    }



    public String getNameGrup() {
        return nameGrup;
    }


    public void setId(String id) {
        this.id = id;
    }


    @Override
        public String toString() {
            return "\"" + "message" + this.getId()+"\"" +":{" +
                    "\"" + "text" + "\"" + ":"+  "\"" +this.getText() + "\","+
                    "\"" + "date" +"\"" +":"+ "\"" + this.getDateTime() + "\","+
                    "\"" + "dateString" +"\"" +":"+ "\"" + this.getDateString() + "\","+
                    "\"" + "timeString" +"\"" +":"+ "\"" + this.getTimeString() + "\","+
                    "\"" + "nameGroup" +"\"" +":"+ "\"" + this.getNameGrup() + "\","+
                    "\"" + "senderId" +  "\""+":" +"\""+ getSenderId() + "\","+
                    "\"" + "id" + "\""+":" + "\"" +getId() + "\","+
                    "\"" + "nameSender" +  "\""+":" +"\""+ getNameSender() + "\""+
                    "}";
        }



    public  static class DBMessage {
        public HashMap<String, MessageGrup> map;
    }
}
