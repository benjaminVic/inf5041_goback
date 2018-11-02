package fr.esiea.inf5041.go.goback.room.game_communication;

/**
 * Used to send a msg to clear the board
 */
public class GoClear {

    private String clear;

    public GoClear()
    {
        this.clear = "nothing";
    }

    public GoClear(String begin)
    {
        this.clear = begin;
    }

    public String getClear()
    {
        return clear;
    }
}
