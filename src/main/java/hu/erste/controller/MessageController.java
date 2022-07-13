package hu.erste.controller;

import hu.erste.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping(path = "/create")
    public String createMessage(@RequestParam( required=false ) String subject){
        return messageService.createMessage(subject);
    }

    @Transactional
    @GetMapping(path = "/transactional/create")
    public String createMessageTransactional(@RequestParam( required=false ) String subject){
        return messageService.createMessage(subject);
    }

    @GetMapping(path = "/all")
    public String getMessages(){
        return messageService.queryMessage().map(userEntity -> userEntity.getSubject()).collect(Collectors.joining(" : "));
    }
}
