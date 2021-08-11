package com.whatsappnew.whatsappnewspringmongo.api;



import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.whatsappnew.whatsappnewspringmongo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UsersController {

    private final UserService userService;






    @GetMapping()
    public List<UserOfWhatsapp> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public UserOfWhatsapp getUserById(@PathVariable("id") String id){
        return userService.selectUserById(id)
                .orElse(null);

    }


    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") String id){
        userService.deleteUserById(id);
        return "success delete userId:   " + id;
    }

    @PutMapping("/{id}")
    public UserOfWhatsapp updateUser(@PathVariable("id") String id,@RequestBody UserOfWhatsapp personToUpdate){
        userService.updateUserById(id,personToUpdate);
        return personToUpdate;
    }

}
