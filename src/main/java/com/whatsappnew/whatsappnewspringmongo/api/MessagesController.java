package com.whatsappnew.whatsappnewspringmongo.api;

import com.whatsappnew.whatsappnewspringmongo.model.Message.MessageSingel;
import com.whatsappnew.whatsappnewspringmongo.services.MessageService;
import com.whatsappnew.whatsappnewspringmongo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MessagesController {




    private final MessageService messageService;




    @PostMapping()
    public ResponseEntity<List<MessageSingel>> addMessage(@RequestBody MessageSingel messageSingel){
        return messageService.addMessage( messageSingel);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<MessageSingel>> getMessagesByUser(@PathParam("sender") String sender, @PathParam("reciver") String reciver){
        return messageService.getAllMessageByUserId(sender,reciver);
    }

    @DeleteMapping()
    public ResponseEntity deleteMessage(@PathParam("id") String id ,@PathParam("sender") String sender){
        return messageService.deleteMessage(id,sender);
    }

    @GetMapping("getMessagesOfSender")
    public ResponseEntity<List<MessageSingel>> getMessagesOfSender(@PathParam("sender") String sender){
       return messageService.getMessagesOfSender(sender);
    }

}
