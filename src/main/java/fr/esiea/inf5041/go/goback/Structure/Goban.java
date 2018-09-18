package fr.esiea.inf5041.go.goback.Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Goban implements I_Board {

    /**
     * Enum used to simplify the usage of direction
     */
    enum Axis {
        HAUT(-1,0),
        BAS(+1,0),
        GAUCHE(0,-1),
        DROITE(0,+1);

        private final int posVertical;
        private final int posHorizontal;

        Axis(int posVertical, int posHorizontal) {
            this.posVertical = posVertical;
            this.posHorizontal = posHorizontal;
        }

        public int getVerticalDirection(){
            return this.posVertical;
        }

        public int getHoziontalDirection(){
            return this.posHorizontal;
        }
    }

    private final int sideSize;
    private final int borderLenght = 1;
    private Stones matrix[][];

    /**
     * Builds a Goban
     * @param size : Lenght of the sides of the board
     */
    public Goban (int size) {
        this.sideSize = size+borderLenght+borderLenght;
        matrix = new Stones[sideSize][sideSize];
        int length = 0;
        int width = 0;
        for (length = borderLenght ; length < (sideSize - borderLenght) ; length++) {
            for (width = borderLenght ; width < (sideSize - borderLenght) ; width++) {
                matrix[length][width] = new Stones(Stones.Color.EMPTY);
            }
        }

        //Left and right out of board
        for (length = 0; length < sideSize ; length++) {
            matrix[length][0] = new Stones(Stones.Color.OUTOFBOARD);
            matrix[length][sideSize-1] = new Stones(Stones.Color.OUTOFBOARD);
        }

        //Top and bottom out of board
        for (width = 0; width < sideSize ; width++) {
            matrix[0][width] = new Stones(Stones.Color.OUTOFBOARD);
            matrix[sideSize-1][width] = new Stones(Stones.Color.OUTOFBOARD);
        }

    }

    public void placeStone(Stones.Color color, int x, int y){
        setStone(color, x, y);
        ArrayList<IntPair> structureList = new ArrayList<>();
        structureList.add(new IntPair(x,y));
        boolean isSuicide = checkIfSuicide(x,y,structureList);
        if (isSuicide) {
            for (IntPair ip : structureList){
                removeStone(ip.getPosVertical(), ip.getPosHorizontal());
            }
        } //else nothing happens
    }

    /**
     * Calculates the degree of freedom of a structure
     * @param x : Vertical position
     * @param y : Vertical position
     * @return int : Nb of degree of freedom of a structure
     */
    public int getDegreeOfFreedomAndUpdateMap(int x, int y, ArrayList<IntPair> structureList){
        int degreeOfFreedom = 0;
        degreeOfFreedom = getDegreeOfFreedomAndUpdateMap(x, y, degreeOfFreedom, structureList);
        return degreeOfFreedom;
    }

    /**
     * Return de degree of freedom of a token structure,
     * the List is filled with the list of positions of the token composing the structure
     * @param x : Vertical position of the token checked
     * @param y : Horizontal position of the token checked
     * @param degreeOfFreedom : the degree of freedom passed as an entry value, used recursivly
     * @param structureList : List containing the structure of the token played, min size = 1
     * @return int : Degree of freedom enjoyed by the structure, if 0 the structure is captured by the opponent
     */
    private int getDegreeOfFreedomAndUpdateMap(int x, int y, int degreeOfFreedom, List<IntPair> structureList) {
        for (Axis axis : Axis.values()) {
            //We set the directions toward which we're gonna look
            int newX = x + axis.getVerticalDirection();
            int newY = y + axis.getHoziontalDirection();

            Stones currentStone = new Stones(matrix[newX][newY]);
            IntPair coordinates = new IntPair(newX, newY);
            if (matrix[x][y].isTheSameColor(currentStone) && !structureList.contains(coordinates)) {
                //If the Stone is not yet registered we add it to the structure and check its neighborhood
                structureList.add(coordinates);
                degreeOfFreedom += getDegreeOfFreedomAndUpdateMap(newX, newY, degreeOfFreedom, structureList);
            } else {
                //Is either worth 0 OR Color.EMPTY and gives +1
                degreeOfFreedom += currentStone.getDegreeOfFreedom();
            }
        }
        return degreeOfFreedom;
    }

    /**
     * Verify if there is a suicide
     * @return True if there is a suicide
     */
    private boolean checkIfSuicide(int x, int y, ArrayList<IntPair> structureList) {
        Stones.Color stoneColor = matrix[x][y].getColor();

        //TODO Add test of deletion on nearby enemy structures

        int degreeOfFreedom = getDegreeOfFreedomAndUpdateMap(x,y, structureList);
        if (degreeOfFreedom>0){
            return false;
        }
        return true;
    }

    /**
     * Return a stone from the Goban by it's position
     * @param x : Vertical position
     * @param y : Horizontal position
     * @return Stones, the object at pos x,y of the board
     */
    public Stones getStone(int x, int y){
        return matrix[x][y];
    }

    /**
     * Set Value of a Stone
     * @param color : color to set to a piece on the board
     * @param x : vertical position
     * @param y : horizontal position
     */
    private void setStone(Stones.Color color, int x, int y) throws IllegalArgumentException {
        this.matrix[x][y].changeColor(color);
    }

    private void removeStone(int x, int y) throws  IllegalArgumentException {
        this.matrix[x][y].removeStone();
    }

    /**
     * The string representing the GOBAN
     * @return Return the string representing the Goban
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int x=0 ; x < sideSize ; x++){
            for (int y=0 ; y < sideSize ; y++){
                sb.append(matrix[x][y].toString());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Class used to simply the usage of double coordinates in function return value
     */
    private class IntPair {
        // Ideally, name the class after whatever you're actually using
        // the int pairs *for.*
        final int posVertical;
        final int posHorizontal;

        IntPair(int posVertical, int posHorizontal) {
            this.posVertical=posVertical;
            this.posHorizontal=posHorizontal;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof IntPair)) return false;

            IntPair other = (IntPair) obj;

            if (posVertical != other.posVertical
                    || posHorizontal != other.posHorizontal) {
                return false;
            }
            return true;
        }

        public int getPosHorizontal() {
            return posHorizontal;
        }

        public int getPosVertical() {
            return posVertical;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
