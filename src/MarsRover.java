import java.io.*;

/**
 * Created by RTM on 30.08.2017.
 */
public class MarsRover {
    enum CameraView {
        WATCH_NORTH(" V "), WATCH_SOUTH(" ^ "), WATCH_EAST(" < "), WATCH_WEST(" > ");

        CameraView(String designation) {
            this.designation = designation;

        }

        static CameraView getDefault() {
            return WATCH_NORTH;
        }

        static CameraView getCameraViewByNumber(int number) {
            int currentLook = number;
            CameraView result = null;
            switch (currentLook) {
                case 1: {
                    result = WATCH_NORTH;
                    break;
                }
                case 2: {
                    result = WATCH_EAST;
                    break;
                }
                case 3: {
                    result = WATCH_SOUTH;
                    break;
                }
                case 4: {
                    result = WATCH_WEST;
                    break;
                }
            }
            return result;
        }

        static final int MAX_CAMERA_VIEW = 4;
        private String designation;

        public String getDesignation() {
            return designation;
        }

        @Override
        public String toString() {
            return designation;
        }
    }

    private static final int START_X = 0;
    private static final int START_Y = 0;
    private int currentX;
    private int currentY;
    private int viewPosition;

    public MarsRover() {
        this.currentX = 0;
        this.currentY = 0;
        this.viewPosition = 1;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getViewPosition() {
        return viewPosition;
    }

    public void goStraightAhead() {
        CameraView current = CameraView.getCameraViewByNumber(viewPosition);
        switch (current) {
            case WATCH_NORTH: {
                currentY = --currentY;
                break;
            }
            case WATCH_WEST: {
                currentX = --currentX;
                break;
            }
            case WATCH_EAST: {
                currentX = ++currentX;
                break;
            }
            case WATCH_SOUTH: {
                currentY = ++currentY;
                break;
            }
        }
    }

    public void goStraightBehind() {
        CameraView current = CameraView.getCameraViewByNumber(viewPosition);
        switch (current) {
            case WATCH_NORTH: {
                currentY = ++currentY;
                break;
            }
            case WATCH_WEST: {
                currentX = ++currentX;
                break;
            }
            case WATCH_EAST: {
                currentX = --currentX;
                break;
            }
            case WATCH_SOUTH: {
                currentY = --currentY;
                break;
            }
        }
    }

    public void showOnMap() {
        int minXPixels = 3;
        int minYPixels = 3;
        int xFromOrigin = Math.abs(START_X - currentX);
        int yFromOrigin = Math.abs(START_Y - currentY);
        if (minXPixels < xFromOrigin) {
            minXPixels = (int) (xFromOrigin * 1.5d);
        }
        if (minYPixels < yFromOrigin) {
            minYPixels = (int) (yFromOrigin * 1.5d);
        }
        for (int i = -minYPixels; i <= minYPixels; i++) {
            for (int j = -minXPixels; j <= minXPixels; j++) {
                if (i == currentY && j == currentX) {
                    System.out.print(findOutCameraPosition());
                } else if (i == START_Y && j == START_X) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" X ");
                }

            }
            System.out.println();
        }
    }

    public CameraView findOutCameraPosition() {
        return CameraView.getCameraViewByNumber(viewPosition);
    }

    public void rightAndForward() {
        turnCameraRight();
        goStraightAhead();
    }

    public void leftAndForward() {
        turnCameraLeft();
        goStraightAhead();
    }


    private void turnCameraRight() {
        viewPosition = ++viewPosition;
        if (viewPosition > 4) {
            viewPosition = viewPosition - CameraView.MAX_CAMERA_VIEW;
        }
    }

    private void turnCameraLeft() {
        viewPosition = --viewPosition;
        if (viewPosition < 1) {
            viewPosition = viewPosition + CameraView.MAX_CAMERA_VIEW;
        }
    }

    private void executeCommand(String string) {
        switch (string) {
            case "forward": {
                goStraightAhead();
                break;
            }
            case "backward": {
                goStraightBehind();
                break;
            }
            case "left": {
                leftAndForward();
                break;
            }
            case "right": {
                rightAndForward();
                break;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }

    }

    public void investigateMars() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Start investigation!");
        System.out.println("POSSIBLE COMMANDS: forward,backward,left,right,exit");
        showOnMap();
        String s;
        while (!(s = reader.readLine()).equals("exit")) {
            try {
                executeCommand(s);
            } catch (UnsupportedOperationException e) {
                System.out.println("POSSIBLE COMMANDS: forward,backward,left,right,exit");
            }
            showOnMap();
        }
    }

    public static void main(String[] args) throws IOException {
        MarsRover marsRover = new MarsRover();
        marsRover.investigateMars();
    }
}
