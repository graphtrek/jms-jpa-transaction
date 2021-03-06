package hu.erste.repository;

import hu.erste.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, UUID> {

  MessageEntity findBySubject(String subject);
}
