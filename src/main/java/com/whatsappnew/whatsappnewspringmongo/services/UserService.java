package com.whatsappnew.whatsappnewspringmongo.services;
import com.whatsappnew.whatsappnewspringmongo.model.Login.Login;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsappGrup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.whatsappnew.whatsappnewspringmongo.repository.GroupRepo;
import com.whatsappnew.whatsappnewspringmongo.repository.UserRepo;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepo userRepo;
    private final GroupRepo groupRepo;

    public UserService(UserRepo userRepo, GroupRepo groupRepo) {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
    }


        public ResponseEntity addUser(UserOfWhatsapp user){
            UserOfWhatsapp user1 = userRepo.findByEmail(user.getEmail());
            System.out.println(user1);
            if(user1 ==null){
                userRepo.insert(user);
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("There is such a user");

        }

    public ResponseEntity login( Login user){
        UserOfWhatsapp user1 = userRepo.findByEmail(user.getUsername());
        if(user1 !=null){
            if(user1.getPassword().equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Incorrect password");

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body( "There is no such user");
    }

    public List<UserOfWhatsapp> getAllUsers() {
        System.out.println("com.com.whatsappnew.whatsappnewspringmongo.service get all users");
        return userRepo.findAll();
    }

    public Optional<UserOfWhatsapp> selectUserById(String id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(String id) {
         userRepo.deleteById(id);
    }


    public int updateUserById(String id, UserOfWhatsapp user) {
        Optional<UserOfWhatsapp> user1 = userRepo.findById(id);
        if(!user1.isEmpty()){
            System.out.println(user1);
            System.out.println(user);
            user.setId(id);
            userRepo.save(user);
            return 1;
        }
        return -1;
    }


    public ResponseEntity addGroup(UserOfWhatsappGrup userOfWhatsappGrup){
        UserOfWhatsappGrup group = groupRepo.findByName(userOfWhatsappGrup.getName());
        if(group !=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("There is such a name of a group");
        }
        try{
            userOfWhatsappGrup.addUser(userOfWhatsappGrup.getCreator());
            groupRepo.insert(userOfWhatsappGrup);
            System.out.println("success");
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<List<UserOfWhatsappGrup>> getAllGroups(String sender){
       return ResponseEntity.status(HttpStatus.OK).body(groupRepo.findAll().stream().filter(group-> group.getAllUsers().contains(sender)).collect(Collectors.toList()));
    }

    public ResponseEntity getAllUsersByGroup(String name){
        return ResponseEntity.status(HttpStatus.OK).body(groupRepo.findByName(name).getAllUsers());
    }


    public ResponseEntity<String> addUserToGroup(String group , String username){
        UserOfWhatsappGrup  groupName = groupRepo.findByName(group);
        System.out.println("group name:  "+ groupName);
        try {
            groupName.addUser(username);
            groupRepo.save(groupName);
             return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResponseEntity removeUserFromGroup(String group ,String username){
        UserOfWhatsappGrup  groupName = groupRepo.findByName(group);
        try {
            groupName.removeUser(username);
            groupRepo.save(groupName);
            return ResponseEntity.status(HttpStatus.OK).body("success remove user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


}
