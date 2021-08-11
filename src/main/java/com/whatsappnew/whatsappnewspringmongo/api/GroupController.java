package com.whatsappnew.whatsappnewspringmongo.api;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsappGrup;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.whatsappnew.whatsappnewspringmongo.services.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("api/v1/group")
@RestController
@CrossOrigin(origins = "*")
public class GroupController {

    private final UserService userService;

    public GroupController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addGroup( @RequestBody UserOfWhatsappGrup group){
        return userService.addGroup(group);
    }

    @GetMapping()
    public ResponseEntity getAllGroups(@PathParam("sender") String sender){
        return userService.getAllGroups(sender);
    }

    @GetMapping("getAllUsersByGroup")
    public ResponseEntity getAllUsersByGroup(@PathParam("group") String group){
        return userService.getAllUsersByGroup(group);
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUserToGroup(@PathParam("group") String group, @RequestBody UserOfWhatsapp username){
        System.out.println("add group controller");
        return userService.addUserToGroup(group,username.getEmail());
    }

    @DeleteMapping("/removeUser")
    public ResponseEntity removeUserFromGroup(@PathParam("group") String group, @RequestBody UserOfWhatsapp username){
        return userService.removeUserFromGroup(group,username.getEmail());
    }


}
