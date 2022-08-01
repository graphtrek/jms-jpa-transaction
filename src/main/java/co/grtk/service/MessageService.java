package co.grtk.service;

import co.grtk.entity.MessageEntity;
import co.grtk.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Session;
import javax.transaction.Transactional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
  private final JmsTemplate jmsTemplate;
  private final MessageRepository messageRepository;
  private final Queue queue;

  public String createMessage(String subject)  {
    log.info("##################################");
    log.info("JMS Send Message subject: {}", subject);
    log.info("##################################");
    jmsTemplate.convertAndSend(queue,  "JMS Send Message subject: " + subject);
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setSubject(subject);
    String messageId = messageRepository.save(messageEntity).getId().toString();
    if(subject.startsWith("wait")) {
      log.info(subject + " 30 sec");
      try {
        Thread.sleep(30000);
      } catch (Exception ex) {
        log.info("Ignore Exception {}", ex.getMessage() );
      }

    }

    if(subject.startsWith("error"))
      throw new RuntimeException();
    return messageId;
  }

  public Stream<MessageEntity> queryMessage() {
    return StreamSupport.stream(messageRepository.findAll().spliterator(), false);
  }

  @Transactional
  @JmsListener(destination = "${activemq.message-queue}")
  public void receiveMessage(@Payload String payload,
                             @Headers MessageHeaders headers,
                             Message message, Session session) {

    log.info("##################################");
    log.info("JMS Received payload: <" + payload + ">");
    log.info("##################################");

    if(payload.contains("error"))
      throw new RuntimeException();
  }
}