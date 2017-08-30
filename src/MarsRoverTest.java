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

    @Test
    public void goStraightBehindTest() {
        int x = marsRover.getCurrentX();
        int y = marsRover.getCurrentY();
        int position = marsRover.getViewPosition();
        marsRover.goStraightBehind();
        int newX = marsRover.getCurrentX();
        int newY = marsRover.getCurrentY();
        int newPosition = marsRover.getViewPosition();
        boolean result = x == newX && (y - 1 == newY) && position == newPosition;
        Assert.assertTrue(result);
    }

    @Test
    public void rightAndForwardTest() {
        int x = marsRover.getCurrentX();
        int y = marsRover.getCurrentY();
        int position = marsRover.getViewPosition();
        marsRover.rightAndForward();
        int newX = marsRover.getCurrentX();
        int newY = marsRover.getCurrentY();
        int newPosition = marsRover.getViewPosition();
        boolean result = x + 1 == newX && (y == newY) && position + 1 == newPosition;
        Assert.assertTrue(result);
    }

    @Test
    public void leftAndForwardTest() {
        int x = marsRover.getCurrentX();
        int y = marsRover.getCurrentY();
        int position = marsRover.getViewPosition();
        marsRover.leftAndForward();
        int newX = marsRover.getCurrentX();
        int newY = marsRover.getCurrentY();
        int newPosition = marsRover.getViewPosition();
        boolean result = x - 1 == newX && (y == newY) && position + 3 == newPosition;
        Assert.assertTrue(result);
    }


    @Test
    public void findOutCameraPositionTest() {
        Assert.assertEquals(marsRover.findOutCameraPosition(), MarsRover.CameraView.WATCH_NORTH);
        marsRover.rightAndForward();
        Assert.assertEquals(marsRover.findOutCameraPosition(), MarsRover.CameraView.WATCH_EAST);
        marsRover.rightAndForward();
        Assert.assertEquals(marsRover.findOutCameraPosition(), MarsRover.CameraView.WATCH_SOUTH);
        marsRover.rightAndForward();
        Assert.assertEquals(marsRover.findOutCameraPosition(), MarsRover.CameraView.WATCH_WEST);
        marsRover.rightAndForward();
        Assert.assertEquals(marsRover.findOutCameraPosition(), MarsRover.CameraView.WATCH_NORTH);
    }
}
