package co.grtk.service;

import co.grtk.entity.DepositDecreaseEntity;
import co.grtk.repository.DepositDecreaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepositDecreaseService {

    private final DepositDecreaseRepository depositDecreaseRepository;

    public DepositDecreaseEntity create(DepositDecreaseEntity depositDecreaseModel) {
        DepositDecreaseEntity depositDecreaseEntity = DepositDecreaseEntity.builder()
                .amount(depositDecreaseModel.getAmount())
                .lastChange(depositDecreaseModel.getLastChange())
                .applicationId(depositDecreaseModel.getApplicationId())
                .depositAccountIBAN(depositDecreaseModel.getDepositAccountIBAN())
                .eUserId(depositDecreaseModel.getEUserId())
                //.authorizationType(depositDecreaseModel.getAuthorizationType())
                //.backendState(depositDecreaseModel.getBackendState())
                .orderId(depositDecreaseModel.getOrderId())
                .channelId(depositDecreaseModel.getChannelId())
                .closure(depositDecreaseModel.getClosure())
                .state(depositDecreaseModel.getState())
                //.signResult(depositDecreaseModel.getSignResult())
                .proportion(depositDecreaseModel.getProportion())
                .partnerAccountIBAN(depositDecreaseModel.getPartnerAccountIBAN())
                .eUserId(depositDecreaseModel.getEUserId())
                .build();

        DepositDecreaseEntity savedEntity = depositDecreaseRepository.save(depositDecreaseEntity);
        return savedEntity;
    }

    public DepositDecreaseEntity update(DepositDecreaseEntity depositDecreaseModel) {
        DepositDecreaseEntity depositDecreaseEntity =
                depositDecreaseRepository.findByOrderId(depositDecreaseModel.getOrderId()).orElseThrow(() -> new IllegalArgumentException("OrderId not fond"));

        depositDecreaseEntity.setAmount(depositDecreaseModel.getAmount());
        DepositDecreaseEntity savedEntity = depositDecreaseRepository.save(depositDecreaseEntity);
        return savedEntity;
    }

}
