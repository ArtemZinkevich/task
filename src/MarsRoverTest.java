import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by RTM on 30.08.2017.
 */
public class MarsRoverTest {
    private MarsRover marsRover;

    @Before
    public void initMarsRover() {
        marsRover = new MarsRover();
    }

    @After
    public void unInit() {
        marsRover = null;
    }

    @Test
    public void goStraightAheadTest() {
        int x = marsRover.getCurrentX();
        int y = marsRover.getCurrentY();
        int position = marsRover.getViewPosition();
        marsRover.goStraightAhead();

        int newX = marsRover.getCurrentX();
        int newY = marsRover.getCurrentY();
        int newPosition = marsRover.getViewPosition();
        boolean result = x == newX && (y + 1 == newY) && position == newPosition;
        Assert.assertTrue(result);
    }
}
