package fr.esiea.inf5041.go.goback;

import fr.esiea.inf5041.go.goback.Structure.Goban;
import fr.esiea.inf5041.go.goback.Structure.I_Token;
import fr.esiea.inf5041.go.goback.Structure.Stones;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GobanTest {

    @Test
    public void setStone() {
        Goban go = new Goban(9);

        go.placeStone(Stones.Color.BLACK,1,1);
        Stones s1 = go.getStone(1,1);
        assert (s1.getColor().equals(Stones.Color.BLACK));
        try {
            go.placeStone(Stones.Color.WHITE, 1, 1);
        } catch (Exception e) {
            assert (e.getClass() == IllegalArgumentException.class);
        }
        try {
            go.placeStone(Stones.Color.WHITE, 0, 0);
        } catch (Exception e) {
            assert (e.getClass() == IllegalArgumentException.class);
        }
    }

    @Test
    public void getDegreeOfFreedomTest(){
        Goban go = new Goban(9);

        go.placeStone(Stones.Color.BLACK,2,3);
        go.placeStone(Stones.Color.BLACK,2,1);
        go.placeStone(Stones.Color.BLACK,1,2);
        go.placeStone(Stones.Color.BLACK,3,2);
        go.placeStone(Stones.Color.WHITE,2,2);

        assert(go.getStone(2,2).getColor() == Stones.Color.EMPTY);
    }

    @Test
    public void getDegreeOfFreedomTestOnComplexe(){
        Goban go = new Goban(9);

        go.placeStone(Stones.Color.BLACK,2,3);
        go.placeStone(Stones.Color.BLACK,2,1);
        go.placeStone(Stones.Color.BLACK,1,2);
        go.placeStone(Stones.Color.BLACK,3,2);
        go.placeStone(Stones.Color.WHITE,2,2);

        assert(go.getStone(2,2).getColor() == Stones.Color.EMPTY);
    }
}
