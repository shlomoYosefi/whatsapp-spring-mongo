package com.whatsappnew.whatsappnewspringmongo.repository;

import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageGrup;
import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageSingel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface MessagesGroupsRepo extends MongoRepository<MessageGrup,String> {

    List<MessageGrup> findByNameGrup(String name);

}
