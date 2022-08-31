package co.grtk.entity.model;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public enum State {
    TIMEOUT(false),
    REJECTED(false),
    DELETED(false),
    CANCELLED(false),
    REVERTED(false),
    EXPIRED(false),
    DONE(false, REVERTED),
    SPOOLED(true, DONE, CANCELLED, REJECTED),
    ON_HOLD(false, SPOOLED, CANCELLED, REJECTED),
    IN_PROGRESS(false, ON_HOLD, SPOOLED, DONE, TIMEOUT, REJECTED, CANCELLED),
    SENT(false, IN_PROGRESS, DONE, REJECTED),
    CONFIRMED(false, SENT, TIMEOUT),
    OPEN(false, CONFIRMED, TIMEOUT, REJECTED, DELETED, CANCELLED, EXPIRED),
    CREATED(false, OPEN, CONFIRMED, SENT, TIMEOUT, REJECTED, DELETED, CANCELLED, EXPIRED);

    private final Set<State> transitions;

    State(boolean transitionToItselfAllowed, State... transitions) {
        this.transitions = new HashSet<>(Arrays.asList(transitions));
        if (transitionToItselfAllowed) {
            this.transitions.add(this);
        }
    }

    private Set<State> getPossibleNextStates(Set<State> existingResults) {
        transitions.forEach(state -> {
            if (!existingResults.contains(state)) {
                existingResults.add(state);
                existingResults.addAll(state.getPossibleNextStates(existingResults));
            }
        });
        return existingResults;
    }

    /**
     * Validates a state transition
     * <p>
     * If the passed state is present in the transition map of current state or any of the states in there,
     * than return true
     *
     * @param next next state
     * @return true if state present in transitions map
     */
    public boolean isValidTransition(State next) {
        return transitions.contains(next) || getPossibleNextStates(new TreeSet<>()).contains(next);
    }
}
