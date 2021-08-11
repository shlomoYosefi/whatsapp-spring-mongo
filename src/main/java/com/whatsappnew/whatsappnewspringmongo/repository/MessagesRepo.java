package com.whatsappnew.whatsappnewspringmongo.repository;

import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageSingel;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepo extends MongoRepository<MessageSingel,String> {

    List<MessageSingel> findBySenderIdAndReceiverId(String senderId , String reciverId);
    List<MessageSingel> findByReceiverId(String reciverId);
    List<MessageSingel> findBySenderId(String senderId);

}
