package fr.esiea.inf5041.go.goback.room;

import fr.esiea.inf5041.go.goback.room.game_communication.GetMove;
import fr.esiea.inf5041.go.goback.room.game_communication.GoClear;
import fr.esiea.inf5041.go.goback.room.game_communication.GoVerify;
import fr.esiea.inf5041.go.goback.room.game_communication.StartGame;
import fr.esiea.inf5041.go.goback.structure.Goban;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * Controls how the game is running
 */
@Controller
public class RoomController {

    private Goban goTable = null;

    @MessageMapping("/hello")
    @SendTo("/response/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


    @MessageMapping("/begin")
    @SendTo("/response/clear")
    public GoClear begins(StartGame msg) throws Exception{
        if (msg.getStart() == 1) {
            if (goTable == null)
                goTable = new Goban(19);
            goTable.removeAllStone();
            return new GoClear("clear");
        }
        return new GoClear();
    }



    private SimpMessagingTemplate msgMove;
    @MessageMapping("/move")
    @SendTo
    public GoVerify verify(GetMove move) throws Exception{
        goTable.placeStone(move.getColor(), move.getX(), move.getY());
        return new GoVerify();
    }
}
