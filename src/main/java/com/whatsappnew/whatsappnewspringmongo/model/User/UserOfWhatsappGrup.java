package com.whatsappnew.whatsappnewspringmongo.model.User;


import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@NoArgsConstructor
public class UserOfWhatsappGrup{

    @Id
    private String id;
    private String name;
    private  String creator;
//    private Map<String,UserOfWhatsapp> users = new HashMap<>();
    private List<String> users = new ArrayList<>();

    public UserOfWhatsappGrup(String name, String creator) {
        this.name = name;
        this.creator = creator;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public UserOfWhatsappGrup(String name) {
        this.name = name;
    }


    public String addUser(UserOfWhatsapp user){
        if( users.contains(user.getUserName())) {
            return "There is such a user in the system";
        }
        users.add(user.getUserName());
        return "success";
    }



    public String removeUser(String userName){
        this.users.remove(userName);
        return "success";
    }

    public String getName() {
        return name;
    }

    public List<String> getAllUsers() {

        return users;
    }

    public Optional<String> getUser(String username){
        return this.users.stream().filter(user->user.equals(username)).findFirst();
    }


    public  static class DBUserOfWhatsappGrup {
        public HashMap<String, UserOfWhatsappGrup> map;
    }


    @Override
    public String toString() {
        return "UserOfWhatsappGrup{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", users=" + users +
                '}';
    }

    public static class templetAddUserGrup{

        String nameGrup;
        String userName;

        public templetAddUserGrup(String name, String username) {
            this.nameGrup = name;
            this.userName = username;
        }

        public String getName() {
            return nameGrup;
        }

        public String getUserName() {
            return userName;
        }


    }

}

