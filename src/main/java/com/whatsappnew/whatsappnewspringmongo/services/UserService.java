package com.whatsappnew.whatsappnewspringmongo.services;
import com.whatsappnew.whatsappnewspringmongo.model.Login.Login;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsappGrup;
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


        public int addUser(UserOfWhatsapp user){
            System.out.println(userRepo.findAll());
            UserOfWhatsapp user1 = userRepo.findByEmail(user.getEmail());
            System.out.println(user1);
            if(user1 ==null){
                userRepo.insert(user);
                return 1;
            }
            System.out.println("not add");
            return 0;
    }

    public String login( Login user){
        UserOfWhatsapp user1 = userRepo.findByEmail(user.getUsername());
        if(user1 !=null){
            if(user1.getPassword().equals(user.getPassword())) {
                return user1.toString();
            }
            return "Incorrect password";
        }
        return "There is no such user";
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


    public String addGroup(UserOfWhatsappGrup userOfWhatsappGrup){
        UserOfWhatsappGrup group = groupRepo.findByName(userOfWhatsappGrup.getName());
        System.out.println(group);
        if(group !=null){
            return "There is such a name of a group";
        }
        try{
            groupRepo.insert(userOfWhatsappGrup);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<UserOfWhatsappGrup> getAllGroups(String sender){
       return groupRepo.findAll().stream().filter(group-> group.getAllUsers().contains(sender)).collect(Collectors.toList());
    }

    public List<String> getAllUsersByGroup(String name){
        return groupRepo.findByName(name).getAllUsers();
    }


    public String addUserToGroup(String group ,String username){
        UserOfWhatsappGrup  groupName = groupRepo.findByName(group);
        System.out.println("group name:  "+ groupName);
        UserOfWhatsapp  user = userRepo.findByEmail(username);
        try {
            groupName.addUser(user);
            groupRepo.save(groupName);
             return "success add user";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";
    }

    public String removeUserFromGroup(String group ,String username){
        UserOfWhatsappGrup  groupName = groupRepo.findByName(group);
        try {
            groupName.removeUser(username);
            groupRepo.save(groupName);
            return "success remove user";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";
    }


}
