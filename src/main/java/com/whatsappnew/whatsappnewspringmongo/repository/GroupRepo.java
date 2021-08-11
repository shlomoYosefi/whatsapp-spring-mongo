package com.whatsappnew.whatsappnewspringmongo.repository;

import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsappGrup;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface GroupRepo extends MongoRepository<UserOfWhatsappGrup,String> {
    UserOfWhatsappGrup  findByName(String s);
}
