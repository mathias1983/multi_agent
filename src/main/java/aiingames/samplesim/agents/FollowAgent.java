package aiingames.samplesim.agents;

import aiingames.samplesim.Config;
import aiingames.samplesim.simulation.Environment;
import aiingames.samplesim.spatial.*;


import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class FollowAgent implements Agent {

    private final String id;
    private double desiredVx;
    private double desiredVy;
    private int xL = 0;
    private int yL = 0;
    private int xD = 0;
    private int yD = 0;
    private boolean change_direction = false;
    private boolean turn_left = true;
    private boolean turn_right = true;
    private boolean change = true;
    private Map<String, Sensor> sensors = new HashMap<String, Sensor>();
    LinkedList<LightSource> lights;
    public int counter = 0;

    public FollowAgent(String id, LinkedList<LightSource> lights) {
        this.id = id;
        this.lights = lights;
        sensors.put(Config.SENSOR_LEFT, new Sensor(new SensorPoint(1, -1, -1, -1)));
        sensors.put(Config.SENSOR_RIGHT, new Sensor(new SensorPoint(1, -1, -1, -1)));
        sensors.put(Config.LIGHT_SOURCE, new Sensor(new SensorPoint(1, -1, -1, -1)));
    }

    public void update(Environment e) {
        double vx = e.getVx(id);
        double vy = e.getVy(id);

        if (vx == 0 && vy == 0) {
            this.desiredVx = Config.MAX_V * 2 * (Math.random() - 0.5);
            this.desiredVy = Config.MAX_V * 2 * (Math.random() - 0.5);
        }

        Iterator<LightSource> itr = lights.iterator();

        double light_x = 100.0;
        double light_y = 100.0;
        while (itr.hasNext()) {
            Sensor r = itr.next().get_light_source();
            light_x = r.get_sensorPoint().getX();
            light_y = r.get_sensorPoint().getY();
        }

        double agent_left_x = this.get_sensor(Config.SENSOR_LEFT).get_sensorPoint().getX();
        double agent_left_y = this.get_sensor(Config.SENSOR_LEFT).get_sensorPoint().getY();

        double agent_right_x = this.get_sensor(Config.SENSOR_RIGHT).get_sensorPoint().getX();
        double agent_right_y = this.get_sensor(Config.SENSOR_RIGHT).get_sensorPoint().getY();
        
        if ((light_x > 100 && light_y > 100) && (light_x < (Config.WINDOW_WIDTH - 100) && light_y < Config.WINDOW_HEIGHT - 100)) {    
           
            int dist_left_sensor = (int) Math.sqrt(Math.pow(light_x - agent_left_x, 2.0)
                    + Math.pow(light_y - agent_left_y, 2.0));
            int dist_right_sensor = (int) Math.sqrt(Math.pow(light_x - agent_right_x, 2.0)
                    + Math.pow(light_y - agent_right_y, 2.0));


            double[] spot = this.get_normalized_spotted_light_source(light_x, light_y);
            double spotX = spot[0];
            double spotY = spot[1];

            double[] sensor_left = this.get_normmalized_sensor_left();
            double sensor_left_x = sensor_left[0];
            double sensor_left_y = sensor_left[1];

            double[] sensor_right = this.get_normmalized_sensor_right();
            double sensor_right_x = sensor_right[0];
            double sensor_right_y = sensor_right[1];

            double degree_left_sensor = Util.to_degree(sensor_left_x, sensor_left_y);
            double degree_right_sensor = Util.to_degree(sensor_right_x, sensor_right_y);
            double degree_spot = Util.to_degree(spotX, spotY);

            if (!Util.is_in_range(degree_left_sensor, degree_right_sensor, degree_spot)) {

                if ((dist_left_sensor > dist_right_sensor) && dist_left_sensor < Config.ACTION_DISTANCE && turn_right) {
                    change_direction = true;
                    turn_left = false;
                }
                if ((dist_left_sensor < dist_right_sensor) && dist_right_sensor < Config.ACTION_DISTANCE && turn_left) {
                    change_direction = true;
                    turn_right = false;
                }
            } else {
                change_direction = false;
                turn_left = true;
                turn_right = true;
            }

            this.change_velocity(this.xD, this.yD);

        }
    }

    private double[] get_normalized_spotted_light_source(double light_x, double light_y) {
        double spotX = light_x - this.xL;
        double spotY = light_y - this.yL;
        return Util.normalize(spotX, spotY);
    }

    private double[] get_normmalized_sensor_left() {
        double sensor_left_x = this.get_sensor(Config.SENSOR_LEFT).get_sensorPoint().getX() - this.xL;
        double sensor_left_y = this.get_sensor(Config.SENSOR_LEFT).get_sensorPoint().getY() - this.yL;
        return Util.normalize(sensor_left_x, sensor_left_y);
    }

    private double[] get_normmalized_sensor_right() {
        double sensor_right_x = this.get_sensor(Config.SENSOR_RIGHT).get_sensorPoint().getX() - this.xL;
        double sensor_right_y = this.get_sensor(Config.SENSOR_RIGHT).get_sensorPoint().getY() - this.yL;
        
        // if (sensor_right_x == 0.0 && sensor_right_y == 0.0)
            
        return Util.normalize(sensor_right_x, sensor_right_y);
    }

    private void change_velocity(double xD_norm, double yD_norm) {
        double newX;
        double newY;

        if (turn_left && change_direction) {
            double radians_cosA = Math.toRadians(-5);
            // Punkt drehen
            newX = (xD_norm * Math.cos(radians_cosA)) - (yD_norm * Math.sin(radians_cosA));
            newY = (xD_norm * Math.sin(radians_cosA)) + (yD_norm * Math.cos(radians_cosA));
            this.desiredVx = newX;
            this.desiredVy = newY;

        }
        if (turn_right && change_direction) {
            double radians_cosA = Math.toRadians(5);
            newX = (xD_norm * Math.cos(radians_cosA)) - (yD_norm * Math.sin(radians_cosA));
            newY = (xD_norm * Math.sin(radians_cosA)) + (yD_norm * Math.cos(radians_cosA));
            this.desiredVx = newX;
            this.desiredVy = newY;
        }
        
      // System.out.println("Ich will hier hin: " + this.desiredVx + " / " + this.desiredVy);
        
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
        g.setColor(Color.green);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);

        SensorPoint p = this.get_sensor(Config.SENSOR_LEFT).get_sensorPoint();
        p.setOffset_x(x);
        p.setOffset_y(y);
        p.set_x(dx);
        p.set_y(dy);
        p.rotate(Config.SENSOR_ANGLE_LEFT);

        g.setColor(Color.BLUE);
        // Groesse des Sensors berechnen
        int sensor_size = radius / Config.SENSOR_SIZE;
        // Sensor an passende Stelle verschieben
        int sensor_offset = sensor_size / Config.SENSOR_OFFSET;

        g.fillOval((int) p.getX() - sensor_offset, (int) p.getY() - sensor_offset, sensor_size, sensor_size);

        SensorPoint p2 = this.get_sensor(Config.SENSOR_RIGHT).get_sensorPoint();
        p2.setOffset_x(x);
        p2.setOffset_y(y);
        p2.set_x(dx);
        p2.set_y(dy);
        p2.rotate(Config.SENSOR_ANGLE_RIGHT);


        g.fillOval((int) p2.getX() - sensor_offset, (int) p2.getY() - sensor_offset, sensor_size, sensor_size);

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
