package fr.esiea.inf5041.go.goback.room.game_communication;

/**
 * Send the verification of the server to the clients
 */
public class GoVerify {

    private String state;
    private String color;
    private String x;
    private String y;

    public GoVerify() {
    }

    public GoVerify(String color, String x, String y)
    {
        this.color = color;
        this.x = x;
        this.y = y;

    }

    public String getColor() {
        return color;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
