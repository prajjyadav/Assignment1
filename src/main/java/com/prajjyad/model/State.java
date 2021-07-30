package com.prajjyad.model;

public enum State {
    ALIVE("A"), DEAD("D");
    private String state;

    State(String state){
        this.state = state;
    }


    @Override
    public String toString() {
        return state;
    }
}
