package fr.esiea.inf5041.go.goback;

import fr.esiea.inf5041.go.goback.Structure.Stones;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StonesTest {

    @Test
    public void IsTheSameColorTest() {
        Stones s1 = new Stones(Stones.Color.BLACK);
        Stones s2 = new Stones(Stones.Color.WHITE);
        Stones s3 = new Stones(Stones.Color.EMPTY);
        Stones s4 = new Stones(Stones.Color.OUTOFBOARD);

        assert (s1.isTheSameColor(s1));
        assert (!s1.isTheSameColor(s2));
        assert (!s1.isTheSameColor(s3));
        assert (!s1.isTheSameColor(s4));
        assert (s2.isTheSameColor(s2));
        assert (!s2.isTheSameColor(s3));
        assert (!s2.isTheSameColor(s4));
        assert (s3.isTheSameColor(s3));
        assert (!s3.isTheSameColor(s4));
        assert (s4.isTheSameColor(s4));
    }

    @Test
    public void removeStoneTest() {
        Stones s1 = new Stones(Stones.Color.WHITE);
        Stones s2 = new Stones(Stones.Color.EMPTY);

        s1.removeStone();
        assert (s1.getColor()== Stones.Color.EMPTY);

        try {
            s2.removeStone();
        } catch (Exception e) {
            assert(e.getClass()==IllegalArgumentException.class);
        }
    }

    @Test
    public void changeColorTest(){
        Stones s1 = new Stones(Stones.Color.WHITE);
        Stones s2 = new Stones(Stones.Color.EMPTY);

        s2.changeColor(Stones.Color.BLACK);
        assert (s2.getColor()== Stones.Color.BLACK);

        try {
            s1.changeColor(Stones.Color.BLACK);
        } catch (Exception e) {
            assert(e.getClass()==IllegalArgumentException.class);
        }
    }


}
