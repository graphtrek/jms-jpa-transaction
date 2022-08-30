package co.grtk.controller;

import co.grtk.service.MessageService;
import co.grtk.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping(path = "/create")
    public String createMessage(@RequestParam( required=false ) String subject) {
        return messageService.createMessage(subject);
    }

    @Transactional
    @GetMapping(path = "/transactional/create")
    public String createMessageTransactional(@RequestParam( required=false ) String subject) {
        return messageService.createMessage(subject);
    }

    @PutMapping(path = "/update/{id}")
    public String updateMessage(@PathVariable("id") int id, @RequestParam( required=false ) String subject) {
        return messageService.updateMessage(id, subject);
    }

    @Transactional
    @PutMapping(path = "/transactional/update/{id}")
    public String updateMessageTransactional(@PathVariable("id") int id, @RequestParam( required=false ) String subject) {
        return messageService.updateMessage(id, subject);
    }

    @GetMapping(path = "/all")
    public String getMessages(){
        return messageService.queryMessage().map(MessageEntity::getSubject).collect(Collectors.joining(" : "));
    }
}
