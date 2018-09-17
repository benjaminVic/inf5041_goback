package fr.esiea.inf5041.go.goback.Structure;

import javafx.util.Pair;

import java.util.HashMap;

public class Goban implements I_Board {

    private final int sideSize;
    private final int borderLenght = 1;
    private Stones matrix[][];

    /**
     * Builds a Goban
     * @param size : Lenght of the sides of the board
     */
    public Goban (int size) {
        this.sideSize = size+2;
        matrix = new Stone[sideSize][sideSize];
        int length;
        int width;
        for (length = borderLenght ; length < (size - borderLenght) ; length++) {
            for (width = borderLenght ; width < (size - borderLenght) ; width++) {
                matrix[length][width] = new Stones(Stones.Color.EMPTY);
            }
        }

        //Left and right out of board
        for (length = 0; length < size ; length++) {
            matrix[length][0] = new Stones(Stones.Color.OUTOFBOARD);
            matrix[length][size-1] = new Stones(Stones.Color.OUTOFBOARD);
        }

        //Top and bottom out of board
        for (width = 0; width < size ; width++) {
            matrix[0][width] = new Stones(Stones.Color.OUTOFBOARD);
            matrix[size-1][width] = new Stones(Stones.Color.OUTOFBOARD);
        }

    }

    //TODO FIX
    private void first_caller(int x, int y){
        HashMap<Integer, Integer> structureMap = new HashMap<>();
        int degreeOfFreedom = 0;

        degreeOfFreedom = getDegreeOfFreedom(x, y,x-1, y, degreeOfFreedom, structureMap);
        degreeOfFreedom = getDegreeOfFreedom(x, y,x+1, y, degreeOfFreedom, structureMap);
        degreeOfFreedom = getDegreeOfFreedom(x, y,x, y-1, degreeOfFreedom, structureMap);
        degreeOfFreedom = getDegreeOfFreedom(x, y,x, y+1, degreeOfFreedom, structureMap);

        if (degreeOfFreedom == 0) {
            //TODO DELETE
            //TODO UPDATE SCORE
        }

        //TODO ADD RECURSIVE CHECK
    }

    //TODO FIX
    private int getDegreeOfFreedom(int x, int y, int newX, int newY, int degreeOfFreedom, HashMap<Integer, Integer> structureMap) {
        Stones currentStone = new Stones(matrix[newX][newY]);
        if (matrix[x][y].isTheSameColor(currentStone)) {
            structureMap.put(newX, newY);
            return degreeOfFreedom;
        } else {
            return (degreeOfFreedom+currentStone.getDegreeOfFreedom());
        }
    }

    /**
     * Verify if there is a suicide
     * @return True if there is a suicide
     */
    private boolean checkIfSuicide(int x, int y) {
        Stones.Color stoneColor = matrix[x][y].getColor();
        int degreeOfFreedom = 0;
        //TODO get structure
        //TODO get degree of freedom

        return false;
    }

    /**
     * Set Value of a Stone
     * @param color : color to set to a piece on the board
     * @param x : vertical position
     * @param y : horizontal position
     */
    public void setStone (Stones.Color color, int x, int y) {
        try {
            this.matrix[x][y].changeColor(color);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
}
