package com.prajjyad.model;

import com.prajjyad.exceptions.InvalidCellException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

import static java.util.stream.Collectors.joining;

@Getter
public class Grid implements Cloneable{
    private int rows;
    private int cols;

    private Cell[][] cells;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
    }

    public Cell getCell(int rowIndex, int colIndex){
        if(!isSafe(rowIndex,colIndex))
            throw new InvalidCellException("out of bound index");
        return this.cells[rowIndex][colIndex];
    }

    public void setAlive(int rowIndex, int colIndex){
        if(!isSafe(rowIndex,colIndex))
            throw new InvalidCellException("out of bound index");
        this.cells[rowIndex][colIndex].makeAlive();
    }
    public void setDead(int rowIndex, int colIndex){
        if(!isSafe(rowIndex,colIndex))
            throw new InvalidCellException("out of bound index");
        this.cells[rowIndex][colIndex].makeDead();
    }

    public State getState(int rowIndex, int colIndex){
        if(!isSafe(rowIndex,colIndex))
            throw new InvalidCellException("out of bound index");
        return this.cells[rowIndex][colIndex].getState();
    }

    public void randomInit(){
        Random random = new Random();
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                State state = random.nextBoolean() ? State.ALIVE : State.DEAD;
                cells[i][j] = new Cell(state, i, j);
            }
        }
    }

    public void cloneInit(Cell[][] cells){
        for(int i=0;i<this.rows;i++){
            for(int j=0;j<this.cols;j++){
                State state= cells[i][j].isAlive() ? State.ALIVE : State.DEAD;
                this.cells[i][j]=new Cell(state, i,j);
            }
        }
    }

    public boolean isSafe(int rowIndex, int columnIndex){
        if(rowIndex<0 || rowIndex >= this.rows || columnIndex < 0 || columnIndex >= this.cols)
            return false;
        return true;
    }


    public int getNumOfLiveNeighbours(int rowIndex, int columnIndex) {
        int count = 0;
        for(int i=rowIndex-1; i<=rowIndex+1 ;i++){
            for(int j=columnIndex-1; j<=columnIndex+1 ;j++){
                if(isSafe(i,j) && (i!=rowIndex || j!=columnIndex))
                    count+= cells[i][j].isAlive() ? 1 :0;
            }
        }
        return count;
    }


    @Override
    public String toString() {
        return Arrays.stream(cells)
                .map(row -> "[" + Arrays.stream(row)
                        .map(Object::toString)
                        .collect(joining(" ")) + "]"
                )
                .collect(joining("\n"));
    }

    @Override
    public Object clone() {
        Grid cloneGrid = new Grid(this.rows, this.cols);
        cloneGrid.cloneInit(this.cells);
        return cloneGrid;
    }
}
