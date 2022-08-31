package co.grtk.entity;

import co.grtk.entity.model.BackendStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(force = true, access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
public class BackendState implements Serializable {

    @Column(name = "BACKEND_ID")
    private String backendId;

    @Column(name = "BACKEND_STATE")
    @Enumerated(EnumType.STRING)
    private BackendStatus state;

    @Column(name = "BACKEND_LAST_CHANGE")
    private LocalDateTime lastChangeDate;

    @Column(name = "BACKEND_STATE_DETAIL_CODE")
    private String backendStateDetailCode;

    @Column(name = "BACKEND_STATE_DETAIL_TEXT")
    private String backendStateDetailText;
}
