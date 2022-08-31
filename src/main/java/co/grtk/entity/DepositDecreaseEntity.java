package co.grtk.entity;


import co.grtk.entity.model.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true, access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@FieldDefaults(level = PRIVATE)
@ToString
@Entity
@Table(name = "ESL_DEPOSIT_DECREASE")
@EntityListeners(AuditingEntityListener.class)
public class DepositDecreaseEntity {

    @Id
    @SequenceGenerator(name = "DepositDecreaseSeqGen", sequenceName = "ESL_DEPOSIT_DECREASE_S")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DepositDecreaseSeqGen")
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    Long id;

    @Column(name = "E_USER_ID", nullable = false)
    String eUserId;

    @Column(name = "ORDER_ID", nullable = false, updatable = false, length = 32)
    String orderId;

    @Column(name = "CHANNEL_ID", nullable = false)
    String channelId;

    @Column(name = "CREATE_TIMESTAMP", nullable = false)
    @Builder.Default
    LocalDateTime createTimestamp = LocalDateTime.now();

    @Embedded
    Amount amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    State state;

    @Column(name = "LAST_CHANGE", nullable = false)
    @LastModifiedDate
    @Version
    LocalDateTime lastChange;

    @Embedded
    BackendState backendState;

    @Enumerated(EnumType.STRING)
    @Column(name = "SIGN_RESULT")
    SignResult signResult;

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORIZATION_TYPE")
    AuthorizationType authorizationType;

    @Column(name = "APPLICATION_ID", nullable = false)
    String applicationId;

    @Column(name = "DEPOSIT_ACCOUNT_IBAN", nullable = false)
    String depositAccountIBAN;

    @Column(name = "PARTNER_ACCOUNT_IBAN", nullable = false)
    String partnerAccountIBAN;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROPORTION", nullable = false)
    DepositDecreaseProportion proportion;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLOSURE", nullable = false)
    Closure closure;

}
