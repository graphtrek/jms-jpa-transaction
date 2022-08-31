package co.grtk.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignResult {
    CONFIRMED(BackendStatus.SIGN_CONFIRMED),
    REJECTED(BackendStatus.SIGN_REJECTED),
    FAILED(BackendStatus.SIGN_FAILED),
    BANNED(BackendStatus.SIGN_BANNED),
    EXPIRED(BackendStatus.SIGN_EXPIRED);

    private BackendStatus backendStatus;
}
