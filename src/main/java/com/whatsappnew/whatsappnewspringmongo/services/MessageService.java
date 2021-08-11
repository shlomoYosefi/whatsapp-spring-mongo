package com.whatsappnew.whatsappnewspringmongo.services;


import com.whatsappnew.whatsappnewspringmongo.FunctionsGenerics;
import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageGrup;
import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageSingel;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import com.whatsappnew.whatsappnewspringmongo.repository.GroupRepo;
import com.whatsappnew.whatsappnewspringmongo.repository.MessagesGroupsRepo;
import com.whatsappnew.whatsappnewspringmongo.repository.MessagesRepo;
import com.whatsappnew.whatsappnewspringmongo.repository.UserRepo;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    private final MessagesRepo messagesRepo;
    private final MessagesGroupsRepo messagesGroupsRepo;
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;

    public MessageService(MessagesRepo messagesRepo, MessagesGroupsRepo messagesGroupsRepo, UserRepo userRepo, GroupRepo groupRepo) {
        this.messagesRepo = messagesRepo;
        this.messagesGroupsRepo = messagesGroupsRepo;
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
    }

    public ResponseEntity<List<MessageSingel>> addMessage(MessageSingel message){
        message.setReceiverName(userRepo.findByEmail(message.getReceiverId()).getName() +"  " + userRepo.findByEmail(message.getReceiverId()).getLastName());
        message.setSenderName(userRepo.findByEmail(message.getSenderId()).getName() +"  " + userRepo.findByEmail(message.getSenderId()).getLastName());
        message.setDateTime(new Date());
        String[] date = FunctionsGenerics.getDate().split(" ");
        message.setDateString(date[0]);
        message.setTimeStyring(date[2]);
        messagesRepo.insert(message);
        List<MessageSingel> msg = (List<MessageSingel>) getAllMessageByUserId(message.getSenderId(),message.getReceiverId());
        Collections.sort(msg);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    public ResponseEntity<List<MessageSingel>> getAllMessageByUserId(String sender , String reciver){
        List<MessageSingel> newList = Stream.concat(messagesRepo.findBySenderIdAndReceiverId(sender,reciver).stream(), messagesRepo.findBySenderIdAndReceiverId(reciver,sender).stream())
                .collect(Collectors.toList());
        Collections.sort(newList);
        return ResponseEntity.status(HttpStatus.OK).body(newList);
    }

    public ResponseEntity deleteMessage(String id ,String idSender){
        if(messagesRepo.findById(id).get().getSenderId().equals(idSender)){
            messagesRepo.deleteById(id);
            System.out.println("success");
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("You can not delete this message");

    }

    public ResponseEntity<List<MessageSingel>> getMessagesOfSender(String sender){
        List<MessageSingel> newList = Stream.concat(messagesRepo.findBySenderId(sender).stream(), messagesRepo.findByReceiverId(sender).stream())
                .collect(Collectors.toList());
        Collections.sort(newList);
        return ResponseEntity.status(HttpStatus.OK).body(newList);
    }



    public ResponseEntity<List<MessageGrup>> addMessageToGroup(MessageGrup message){
        message.setNameSender(userRepo.findByEmail(message.getSenderId()).getName() +"  " + userRepo.findByEmail(message.getSenderId()).getLastName());
        String[] date = FunctionsGenerics.getDate().split(" ");
        System.out.println(date);
        message.setDateString(date[0]);
        message.setTimeStyring(date[2]);
        messagesGroupsRepo.insert(message);
        return ResponseEntity.status(HttpStatus.OK).body((List<MessageGrup>) getAllMessageByGroup(message.getNameGrup()));
    }

    public ResponseEntity<List<MessageGrup>> getAllMessageByGroup(String name){
        List<MessageGrup> message = messagesGroupsRepo.findByNameGrup(name);
        Collections.sort(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    public ResponseEntity deleteMessageFromGroup(String id, String sender){
        if(messagesGroupsRepo.findById(id).get().getSenderId().equals(sender)){
            messagesGroupsRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("success delete");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("You can not delete this message");
    }


}
