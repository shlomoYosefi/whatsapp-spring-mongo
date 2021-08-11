package com.whatsappnew.whatsappnewspringmongo.api;


import com.whatsappnew.whatsappnewspringmongo.model.Login.Login;
import com.whatsappnew.whatsappnewspringmongo.model.User.UserOfWhatsapp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import com.whatsappnew.whatsappnewspringmongo.services.UserService;


@RequestMapping("api/v1/connect")
@RestController
@CrossOrigin(origins = "*")
public class LogInController {


    private final UserService userService;

    public LogInController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register( @RequestBody UserOfWhatsapp user){
        System.out.println("add user controller");
        System.out.println(user);
        userService.addUser(user);
//        return "success add user:  " + user.toString();
                return "success add user:  " ;

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NonNull Login user){
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(user));
    }

}
