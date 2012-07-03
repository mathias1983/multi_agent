package aiingames.samplesim.agents;

import aiingames.samplesim.Config;
import aiingames.samplesim.simulation.Environment;
import aiingames.samplesim.spatial.Coordinate;
import aiingames.samplesim.spatial.Sensor;
import aiingames.samplesim.spatial.SensorPoint;


import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class SimpleAgent implements Agent {

    private final String id;
    private double desiredVx;
    private double desiredVy;
    private Map<String, Sensor> sensors = new HashMap<String, Sensor>();
    private int xL = 0;
    private int yL = 0;
    private int xD = 0;
    private int yD = 0;

    public SimpleAgent(String id) {
        this.id = id;
        sensors.put(Config.SENSOR_LEFT, new Sensor(new SensorPoint(0, 0, 0, 0)));
        sensors.put(Config.SENSOR_RIGHT, new Sensor(new SensorPoint(0, 0, 0, 0)));
    }

    public void update(Environment e) {
        // @todo
        //sensors.clear();
        //
        double vx = e.getVx(id);
        double vy = e.getVy(id);

        if (vx == 0 && vy == 0) {
            this.desiredVx = Config.MAX_V * 2 * (Math.random() - 0.5);
            this.desiredVy = Config.MAX_V * 2 * (Math.random() - 0.5);
        }
    }

    public Sensor get_sensor(String sensor) {

        return sensors.get(sensor);
    }

    public String getId() {
        return this.id;
    }

    public double getDesiredVx() {
        return this.desiredVx;
    }

    public double getDesiredVy() {
        return this.desiredVy;
    }

    public void paint(Graphics g, int radius, int x, int y, int dx, int dy) {
        g.setColor(Color.BLUE);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);

    }

    public void setDirection(int x, int y) {
        this.xD = x;
        this.yD = y;

    }

    public void setLocation(int x, int y) {
        this.xL = x;
        this.yL = y;

    }
}
