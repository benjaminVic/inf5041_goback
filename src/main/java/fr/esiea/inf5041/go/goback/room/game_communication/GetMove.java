package fr.esiea.inf5041.go.goback.room.game_communication;

import fr.esiea.inf5041.go.goback.structure.I_Token;
import fr.esiea.inf5041.go.goback.structure.Stones;

/**
 * Retrieve the move done by a player
 */
public class GetMove {

    private String color;
    private int x;
    private int y;

    public GetMove()
    {}

    public GetMove(String color, String x, String y)
    {
        this.color = color;
        this.x = Integer.decode(x) - 1; //Javascript index begins at 1, Java at 0
        this.y = Integer.decode(y) - 1;
    }

    public Stones.Color getColor() {
        if (color == "black")
            return I_Token.Color.BLACK;
        if (color == "white")
            return I_Token.Color.WHITE;
        return I_Token.Color.EMPTY;
    }

    public String getStringColor()
    {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
