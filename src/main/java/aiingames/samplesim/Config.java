package aiingames.samplesim;

public abstract class Config {

    public static final double TAU = 0.5;
    public static final double MAX_V = 3;
    public static final String SENSOR_RIGHT = "SENSOR_RIGHT";
    public static final String SENSOR_LEFT = "SENSOR_LEFT";
    public static final String LIGHT_SOURCE = "LIGHT_SOURCE";
    public static final int SENSOR_SIZE = 3;
    public static final int SENSOR_OFFSET = 2;
    public static final int LIGHT_SOURCE_SIZE = 1;
    public static final int LIGHT_SOURCE_OFFSET = 2;
    public static final int ACTION_DISTANCE = 500;
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    public static final double SENSOR_ANGLE_LEFT = -30.0;
    public static final double SENSOR_ANGLE_RIGHT = 30.0;
    public static final double LIGHT_SOURCE_ANGLE = 180.0;
    private static double SIM_STEP_SIZE = 0.05;
    private static double STOP_TIME = 100;

    public static void setSimStepSize(double simStepSize) {
        SIM_STEP_SIZE = simStepSize;
    }

    public static double getSimStepSize() {
        return SIM_STEP_SIZE;
    }

    public static void setStopTime(double time) {
        STOP_TIME = time;
    }

    public static double getStopTime() {
        return STOP_TIME;
    }
}
