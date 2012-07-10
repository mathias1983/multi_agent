package aiingames.samplesim;

//import org.apache.log4j.Logger;
import java.util.LinkedList;

import aiingames.samplesim.agents.*;
import aiingames.samplesim.gui.Gui;
import aiingames.samplesim.simulation.Environment;
import aiingames.samplesim.simulation.Simulation;
import aiingames.samplesim.spatial.CircleCut2;
import aiingames.samplesim.spatial.Coordinate;
import aiingames.samplesim.spatial.Path;
import aiingames.samplesim.spatial.Util;

public class Controller {

    // private static final Logger log = Logger.getLogger(Controller.class);
    public void run() {
        //	 log.info("creating GUI...");
        //	 System.out.println(Math.toRadians(360) -  Math.acos( (-0.7547) ));

        Gui gui = new Gui();

        //	 log.info("creating environment ...");
        Environment e = new Environment();
        gui.setEnvironmentSize(e.getMinX(), e.getMinY(), e.getMaxX(), e.getMaxY());

        //	 log.info("creating sim ...");
        Simulation sim = new Simulation(e);
        sim.setGui(gui);

        SimpleLightSource light_1 = new SimpleLightSource("LightSource_1");
        sim.addAgent(light_1, new Coordinate(5, 5));

        LinkedList<LightSource> lights = new LinkedList<LightSource>();
        lights.add(light_1);

        Agent follow_1 = new FollowAgent("FollowAgent_1", lights);
        sim.addAgent(follow_1, new Coordinate(get_random_value(), get_random_value()));

        Agent follow_2 = new FollowAgent("FollowAgent_2", lights);
        sim.addAgent(follow_2, new Coordinate(get_random_value(), get_random_value()));

        Agent follow_3 = new FollowAgent("FollowAgent_3", lights);
        sim.addAgent(follow_3, new Coordinate(get_random_value(), get_random_value()));

        Agent follow_4 = new FollowAgent("FollowAgent_4", lights);
        sim.addAgent(follow_4, new Coordinate(get_random_value(), get_random_value()));
        
        Path path = new Path();
      //  path.get_intersection_point( new Coordinate( 0,1 ), new Coordinate( 4,5.5 ),  new Coordinate( 0,5 ), new Coordinate( 4,3 ) );
        
        path.get_shortest_vector_to_path( new Coordinate( 0,1 ), new Coordinate( 4,5.5 ), new Coordinate( 3,5 ) );
        
        //CircleCut2.calc(100.0, 100.0, 50.0, 10.0, 245.0, 50.0);
        
        CircleCut2.calc_intersections( new Coordinate( 0,-3 ),  new Coordinate( 0,0 ), 5,5 );
        
        double test = Util.get_length(-9, 1, 5, -5);
        
//
//        Agent follow_5 = new FollowAgent("FollowAgent_5", lights);
//        sim.addAgent(follow_5, new Coordinate(get_random_value(), get_random_value()));
//
//        Agent follow_6 = new FollowAgent("FollowAgent_6", lights);
//        sim.addAgent(follow_6, new Coordinate(get_random_value(), get_random_value()));

        //	 log.info("creating agents ...");
        Agent simple_1 = new SimpleAgent("simple");
        sim.addAgent(simple_1, new Coordinate(get_random_value(), get_random_value()));

        //     Agent agent2 = new SimpleAgent("simple2");
        //     sim.addAgent(agent2, new Coordinate(7,7));



        //	 log.info("starting sim ...");
        sim.run();

        //	 log.info("done.");


    }

    public static void main(String[] args) {
        new Controller().run();
    }

    public static double get_random_value() {
        return (Math.random() + 0.1) * 10;
    }
}
