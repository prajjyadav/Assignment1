package com.prajjyad.rules;

import com.prajjyad.model.State;

import java.util.HashMap;
import java.util.Map;

public class RulesImplementation implements Rules {
    public static final Map<State,Map<Integer,State>> RULES = new HashMap<>();
    static {
        Map<Integer, State> aliveRules = new HashMap<>();
        aliveRules.put(0, State.DEAD);
        aliveRules.put(1, State.DEAD);
        aliveRules.put(2, State.ALIVE);
        aliveRules.put(3, State.ALIVE);
        aliveRules.put(4, State.DEAD);
        aliveRules.put(5, State.DEAD);
        aliveRules.put(6, State.DEAD);
        aliveRules.put(7, State.DEAD);
        aliveRules.put(8, State.DEAD);
        Map<Integer, State> deadRules = new HashMap<>();
        deadRules.put(0, State.DEAD);
        deadRules.put(1, State.DEAD);
        deadRules.put(2, State.DEAD);
        deadRules.put(3, State.ALIVE);
        deadRules.put(4, State.DEAD);
        deadRules.put(5, State.DEAD);
        deadRules.put(6, State.DEAD);
        deadRules.put(7, State.DEAD);
        deadRules.put(8, State.DEAD);
        RULES.put(State.ALIVE, aliveRules);
        RULES.put(State.DEAD, deadRules);
    }

    @Override
    public State predict(State currState, int aliveNeighbours) {
        return RULES.get(currState).get(aliveNeighbours);
    }
}
