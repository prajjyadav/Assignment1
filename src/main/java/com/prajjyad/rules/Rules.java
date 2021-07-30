package com.prajjyad.rules;

import com.prajjyad.model.State;

public interface Rules {
    State predict(State currState, int aliveNeighbours);
}
