package co.grtk.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String subject;
}
