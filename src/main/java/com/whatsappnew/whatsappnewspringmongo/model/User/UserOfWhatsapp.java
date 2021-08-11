package com.whatsappnew.whatsappnewspringmongo.model.User;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Document
public class UserOfWhatsapp extends User {


    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String phone;





    public UserOfWhatsapp( String name, String lastName, String email,String phone ,String password) {
        super(email, password);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void  setUserName(){
        this.setUserName(this.email);
    }

    @Override
    public String toString() {
        return "\"" + this.getUserName()+"\"" +":{" +
                "\"" + "name" + "\"" + ":"+  "\"" +this.getName() + "\","+
                "\"" + "lastname" + "\"" + ":"+  "\"" +this.getLastName() + "\","+
                "\"" + "email" + "\"" + ":"+  "\"" +this.getEmail() + "\","+
                "\"" + "phone" + "\"" + ":"+  "\"" +this.getPhone() + "\"" +
                "}";
    }



}
