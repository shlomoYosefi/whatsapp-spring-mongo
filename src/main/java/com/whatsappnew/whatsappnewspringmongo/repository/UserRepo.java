package com.whatsappnew.whatsappnewspringmongo.repository;

import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserOfWhatsapp,String> {
    UserOfWhatsapp findByEmail(String s);

}
