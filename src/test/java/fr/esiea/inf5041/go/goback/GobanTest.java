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
        go.placeStone(Stones.Color.WHITE,2,2);
        go.placeStone(Stones.Color.WHITE,3,2);
        go.placeStone(Stones.Color.WHITE,4,2);
        go.placeStone(Stones.Color.WHITE,5,2);
        go.placeStone(Stones.Color.WHITE,6,3);
        go.placeStone(Stones.Color.WHITE,6,4);
        go.placeStone(Stones.Color.WHITE,6,5);
        go.placeStone(Stones.Color.WHITE,6,2);
        go.placeStone(Stones.Color.BLACK,6,7);
        go.placeStone(Stones.Color.BLACK,7,2);
        go.placeStone(Stones.Color.BLACK,7,3);
        go.placeStone(Stones.Color.BLACK,7,4);
        go.placeStone(Stones.Color.BLACK,7,5);
        go.placeStone(Stones.Color.BLACK,7,6);
        go.placeStone(Stones.Color.WHITE,3,1);
        go.placeStone(Stones.Color.BLACK,4,1);
        go.placeStone(Stones.Color.BLACK,5,1);
        go.placeStone(Stones.Color.BLACK,3,3);
        go.placeStone(Stones.Color.BLACK,4,3);
        go.placeStone(Stones.Color.WHITE,5,3);
        go.placeStone(Stones.Color.BLACK,5,4);
        go.placeStone(Stones.Color.BLACK,5,5);
        go.placeStone(Stones.Color.BLACK,5,6);
        go.placeStone(Stones.Color.BLACK,6,1);
        System.out.println(go.toString());
        go.placeStone(Stones.Color.WHITE,6,6);
        System.out.println(go.toString());
        //assert(go.getStone(2,2).getColor() == Stones.Color.EMPTY);
    }
}
