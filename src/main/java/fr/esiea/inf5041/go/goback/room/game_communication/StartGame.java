package fr.esiea.inf5041.go.goback.room.game_communication;

/**
 * Receive a msg from client he wants to go back to new game
 */
public class StartGame {

    private int start;

    public StartGame()
    {
    }

    public StartGame(int start)
    {
        this.start = start;
    }

    public int getStart()
    {
        return start;
    }
}
