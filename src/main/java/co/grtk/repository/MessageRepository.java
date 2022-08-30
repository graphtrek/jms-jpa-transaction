package co.grtk.repository;

import co.grtk.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {

  MessageEntity findBySubject(String subject);
  Optional<MessageEntity> findById(Integer id);
}
