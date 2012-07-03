package aiingames.samplesim.spatial;

/**
 * Created by IntelliJ IDEA. User: mathias Date: 4/9/12 Time: 11:46 AM To change
 * this template use File | Settings | File Templates.
 */
public class SensorPoint extends Coordinate {

    private double offset_x = 0.0;
    private double offset_y = 0.0;

    public void setOffset_x(double offset_x) {
        this.offset_x = offset_x;
    }

    public void setOffset_y(double offset_y) {
        this.offset_y = offset_y;
    }

    public void set_x(double x) {
        this.x = x;
    }

    public void set_y(double y) {
        this.y = y;
    }

    public SensorPoint(double x, double y, double offset_x, double offset_y) {
        super(x, y);
        if ((int) offset_x >= 0) {
            this.offset_x = offset_x;
        } else {
            this.offset_x = 0.0;
        }

        if ((int) offset_y >= 0) {
            this.offset_y = offset_y;
        } else {
            this.offset_y = 0.0;
        }

    }

    public SensorPoint rotate(double angle) {
        if ((int) angle >= 360) {
            angle = 360.0;
        }
        if ((int) angle <= -360) {
            angle = -360;
        }

        // ohne offset!!
        double x = this.getX() == 0 ? 1.0 : this.getX();
        double y = this.getY() == 0 ? 1.0 : this.getY();

        // vektor normieren
        double abs_value = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));

         x /= abs_value;
         y /= abs_value;

        // rad aus deg berechnen
        double radians_cosA = Math.toRadians(angle);

        // Punkt drehen
        double newX = (x * Math.cos(radians_cosA)) - (y * Math.sin(radians_cosA));
        double newY = (x * Math.sin(radians_cosA)) + (y * Math.cos(radians_cosA));

        // offset wieder hinzufügen
        final Object obj = new Object();

        synchronized (obj) {
            this.setX(((newX * abs_value) + this.offset_x));
            this.setY(((newY * abs_value) + this.offset_y));
        }

        return this;
    }
}
