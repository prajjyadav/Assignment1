package com.prajjyad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cell {
    private State state;
    private final int rowNo;
    private final int colNo;

    // created two methods to change state so that cell should not enter into invalid state
    public void makeAlive(){
        this.state = State.ALIVE;
    }
    public void makeDead(){
        this.state = State.DEAD;
    }

    public boolean isAlive(){
        return this.state == State.ALIVE;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
