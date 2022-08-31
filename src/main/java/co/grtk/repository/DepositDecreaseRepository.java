package co.grtk.repository;


import co.grtk.entity.DepositDecreaseEntity;
import co.grtk.entity.model.State;
import com.google.common.collect.ImmutableSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DepositDecreaseRepository extends JpaRepository<DepositDecreaseEntity, Long> {

    Set<State> END_ORDER_STATUSES = ImmutableSet.of(State.DONE, State.CANCELLED, State.REJECTED, State.REVERTED, State.DELETED);

    default List<DepositDecreaseEntity> findPendingStateDepositDecreases(Set<String> depositAccountIbans) {
        return findAllByDepositAccountIBANInAndStateNotIn(depositAccountIbans, END_ORDER_STATUSES);
    }

    List<DepositDecreaseEntity> findAllByDepositAccountIBANInAndStateNotIn(Set<String> depositAccountIbans, Set<State> states);

    Optional<DepositDecreaseEntity> findByOrderId(String orderId);

    List<DepositDecreaseEntity> findAllByStateAndLastChangeBefore(State state, LocalDateTime timeoutThresholdTs);

    default int deleteExpiredTransactions(LocalDateTime clearThresholdTs) {
        return deleteExpiredTransactions(clearThresholdTs, END_ORDER_STATUSES);
    }

    @Modifying
    @Query(value = "DELETE FROM DepositDecreaseEntity o WHERE o.lastChange < :clearThresholdTs AND o.state IN :endStatuses")
    int deleteExpiredTransactions(@Param("clearThresholdTs") LocalDateTime clearThresholdTs,
                                  @Param("endStatuses") Set<State> endStatuses);
}
