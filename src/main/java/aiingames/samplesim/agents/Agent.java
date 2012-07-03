package aiingames.samplesim.agents;


import java.awt.Graphics;

import aiingames.samplesim.simulation.Environment;
import aiingames.samplesim.spatial.Coordinate;
import aiingames.samplesim.spatial.Sensor;

public interface Agent {

	void update(Environment e);
	
	public String getId();
	
	public double getDesiredVx();
	
	public double getDesiredVy();

    public Sensor get_sensor(String sensor);
    
    public void paint( Graphics g, int radius, int x, int y, int dx, int dy );

	void setDirection(int x, int y);

	void setLocation(int x, int y);

}
