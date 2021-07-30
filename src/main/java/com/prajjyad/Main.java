package com.prajjyad;

import com.prajjyad.exceptions.InvalidCellException;
import com.prajjyad.model.State;
import com.prajjyad.rules.Rules;
import com.prajjyad.rules.RulesImplementation;
import com.prajjyad.service.LifeCycle;

import java.util.Scanner;

public class Main {

    private enum Operations{
        SET_DEAD,SET_ALIVE;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to generation game");
        System.out.println("Enter number of rows in grid: ");
        int rows = scanner.nextInt();
        System.out.println("Enter number of columns in grid: ");
        int cols = scanner.nextInt();
        Rules rules = new RulesImplementation();
        LifeCycle lifeCycle = new LifeCycle(rules, rows, cols);
        printOptions();
        while(true){
            int option = scanner.nextInt();
            if(option == 4)
                break;
            int row, col;
            switch (option){
                case 1:
                    lifeCycle.displayStatus();
                    break;
                case 2:
                    System.out.println("Enter row of cell:");
                    row = scanner.nextInt();
                    System.out.println("Enter column of cell:");
                    col = scanner.nextInt();
                    try{
                        State state = lifeCycle.search(row, col);
                        System.out.println(state);
                    } catch (InvalidCellException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter the number of operations to perform: ");
                    int count = scanner.nextInt();
                    for(int i=0;i<count;i++){
                        try{
                            performOperations(lifeCycle);
                        } catch (InvalidCellException e){
                            System.out.println(e.getMessage());
                            System.out.println("Please Enter valid row and columns");
                        }
                    }
                    lifeCycle.next();
                    break;
                case 5:
                    lifeCycle.next();
                    break;
                case 9:
                default:
                    printOptions();
            }
        }
    }

    private static void printOptions(){
        System.out.println("Following are the operations that you can perform:");
        System.out.println("Press 1 to show status of all cells");
        System.out.println("Press 2 to search for specific cell");
        System.out.println("Press 3 to make a cell alive/dead");
        System.out.println("Press 4 to exit");
        System.out.println("Press 5 to jump to next generation");
        System.out.println("Press 9 to show options any time again");
    }

    private static void performOperations(LifeCycle lifeCycle){
        int operation = acceptInput("Enter 1 to make cell alive, 2 to make cell dead");

        int row = acceptInput("Enter row of cell:");
        int col = acceptInput("Enter column of cell:");

        switch (operation){
            case 1:
                lifeCycle.setAlive(row, col);
                break;
            case 2:
                lifeCycle.setDead(row, col);
                break;
            default:
                System.out.println("invalid choice");
                break;
        }

    }

    private static int acceptInput(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }

}
