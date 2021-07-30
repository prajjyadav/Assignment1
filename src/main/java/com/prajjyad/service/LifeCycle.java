package com.prajjyad.service;

import com.prajjyad.model.Grid;
import com.prajjyad.model.State;
import com.prajjyad.rules.Rules;

public class LifeCycle {
    private Rules rules;
    private Grid grid;

    public LifeCycle(Rules rules, int rows, int cols){
        this.rules = rules;
        this.grid = new Grid(rows, cols);
        this.grid.randomInit();
    }

//    public LifeCycle(Rules rules, Grid grid){
//        this.rules = rules;
//        this.grid = grid;
//    }

    public State search(int rowIndex, int colIndex){
        return grid.getCell(rowIndex-1, colIndex-1).getState();
    }

    public void setAlive(int rowIndex, int colIndex){
        grid.setAlive(rowIndex-1, colIndex-1);
    }

    public void setDead(int rowIndex, int colIndex){
        grid.setDead(rowIndex-1, colIndex-1);
    }

    public void displayStatus(){
        System.out.println(grid);
    }

    public void next(){
        Grid newGrid = (Grid) grid.clone();
        int rows = grid.getRows();
        int cols = grid.getCols();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int aliveNeighbours = grid.getNumOfLiveNeighbours(i,j);
                State nextState = rules.predict(grid.getState(i,j), aliveNeighbours);
                if(nextState==State.ALIVE){
                    newGrid.setAlive(i,j);
                }
                else if(nextState == State.DEAD){
                    newGrid.setDead(i,j);
                }
            }
        }
        grid = newGrid;
    }


}
