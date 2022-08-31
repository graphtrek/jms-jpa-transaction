package co.grtk.entity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public enum BackendStatus implements Serializable {
    PARSED_OK(State.IN_PROGRESS),
    BEING_PROCESSED(State.SPOOLED),
    REJECTED(State.REJECTED),
    COMPLETED(State.DONE),
    INVALID_DATA(State.REJECTED),
    TIMEOUT(State.TIMEOUT),
    ON_HOLD(State.ON_HOLD),
    ABOVE_LIMIT(State.REJECTED),
    PARSED_WITH_ERRORS(State.REJECTED),
    UNKNOWN(State.REJECTED),
    SIGN_CONFIRMED(State.CONFIRMED),
    SIGN_FAILED(State.REJECTED),
    SIGN_BANNED(State.REJECTED),
    SIGN_REJECTED(State.CANCELLED),
    SIGN_EXPIRED(State.EXPIRED),
    AMTR_REJECTED(State.REJECTED);

    @Getter
    private final State state;
}
