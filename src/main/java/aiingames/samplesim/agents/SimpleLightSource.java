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

public class SimpleLightSource implements Agent, LightSource {

    private final String id;
    private double desiredVx;
    private double desiredVy;
    private Sensor lightSource;
    private int xL = 0;
    private int yL = 0;
    private int xD = 0;
    private int yD = 0;

    public SimpleLightSource(String id) {
        this.id = id;
        lightSource = new Sensor(new SensorPoint(1, 1, 1, 1));

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

        // ....
        double pos_x = e.getAgentLocation(this.getId()).getX();

        double pos_y = e.getAgentLocation(this.getId()).getY();



    }

    public Sensor get_sensor(String sensor) {
        return this.get_light_source();
    }

    public Sensor get_light_source() {
        return lightSource;
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
        //g.setColor(Color.ORANGE);
        //g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
        //g.setColor(Color.BLACK);

        // Groesse des Sensors berechnen
        int sensor_size = radius / Config.LIGHT_SOURCE_SIZE;
        // Sensor an passende Stelle verschieben
        int sensor_offset = 0;//sensor_size / Config.LIGHT_SOURCE_OFFSET;

        SensorPoint light_source = this.get_sensor(Config.LIGHT_SOURCE).get_sensorPoint();
        light_source.setOffset_x(x);
        light_source.setOffset_y(y);
        light_source.set_x(dx);
        light_source.set_y(dy);
        light_source.rotate(Config.LIGHT_SOURCE_ANGLE);

        g.setColor(Color.ORANGE);
        g.fillOval((int) light_source.getX() - sensor_offset, (int) light_source.getY() - sensor_offset, sensor_size, sensor_size);
        g.setColor(Color.YELLOW);
        g.fillOval((int) light_source.getX() - sensor_offset + sensor_size / 4, (int) light_source.getY() - sensor_offset + sensor_size / 4, sensor_size / 2, sensor_size / 2);

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
