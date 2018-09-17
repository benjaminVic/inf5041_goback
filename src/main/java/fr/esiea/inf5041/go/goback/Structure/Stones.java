package fr.esiea.inf5041.go.goback.Structure;

public class Stones implements I_Token {

    private Stones.Color color;

    /**
     * Create a new Stone with the specified color
     * @param color
     */
    public Stones (Color color) {
        this.color = color;
    }

    public Stones (Stones stone){
        this.color = stone.color;
    }

    /**
     * Return the color of the stone
     * @return Color of the stone
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Check whether your StoneColor to another stone's color
     * @param stone : Stone other than your actual
     * @return True if your stones have the same color, else False.
     */
    public boolean isTheSameColor(Stones stone){
        if (this.color == stone.getColor()) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param nextColor
     * @throws IllegalArgumentException
     */
    public void changeColor(Stones.Color nextColor) throws IllegalArgumentException{
        if (this.color == Stones.Color.EMPTY) {
            this.setColor(nextColor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return degrree of 'MURICA
     * @return Muh Freedom
     */
    public int getDegreeOfFreedom(){
        return this.color.getDegreeOfFreedom();
    }

    /**
     * Sets the color of a stone
     * @param nextColor : Color to set on the stone
     */
    private void setColor(Color nextColor){
        this.color = nextColor;
    }
}
