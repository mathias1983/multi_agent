package aiingames.samplesim.spatial;

/**
 * Created by IntelliJ IDEA. User: mathias Date: 4/9/12 Time: 11:44 AM To change
 * this template use File | Settings | File Templates.
 */
public class Sensor {

    private SensorPoint point;

    public Sensor(SensorPoint point) {
        this.point = point;
    }

    public SensorPoint get_sensorPoint() {
        return this.point;
    }
}
