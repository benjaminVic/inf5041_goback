package fr.esiea.inf5041.go.goback.room.game_communication;

/**
 * Send the verification of the server to the clients
 */
public class GoVerify {

    private String state;
    private String color;
    private int x;
    private int y;

    public GoVerify() {
        this.state = "nothing";
        this.color = "nothing";
        x = 1;
        y = 1;
    }

    public GoVerify(String state, String color, int x, int y)
    {
        this.state = state;
        this.color = color;
        this.x = x; //Because Javascript index starts at 1, whether Java starts at 0
        this.y = y;

    }

    public String getState() { return state; }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
