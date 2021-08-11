package com.whatsappnew.whatsappnewspringmongo.api;

import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageGrup;
import com.whatsappnew.whatsappnewspringmongo.services.MessageService;
import com.whatsappnew.whatsappnewspringmongo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/api/v1/messagesGroups")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MessagesGroupsController {

    private final MessageService messageService;





    @PostMapping()
    public List<MessageGrup> addMessage(@RequestBody MessageGrup messageGrup){
        return messageService.addMessageToGroup( messageGrup);
    }

    @GetMapping("/getMessagesByGroup")
    public List<MessageGrup> getMessagesByUser(@PathParam("group") String group){
        System.out.println(group);
        return messageService.getAllMessageByGroup(group);
    }

    @DeleteMapping()
    public ResponseEntity deleteMessage(@PathParam("id") String id , @PathParam("sender") String sender){
        return messageService.deleteMessageFromGroup(id,sender);
    }


}
