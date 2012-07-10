package aiingames.samplesim.spatial;



public class CircleCut2 {// calculates the cutting points of two circles...
	
		/**
		 * Mittelpunkt zwischen zwei Punkten ermitteln.
		 * @param coA
		 * @param coB
		 * @return
		 */
		public static Coordinate get_centerpoint( Coordinate coA, Coordinate coB )
		{
			double x = coA.getX() - coB.getX();
			double y = coA.getY() - coB.getY();
			
			return new Coordinate(coB.getX()+(x/2.0), coB.getY()+(y/2.0));
		}
	
		/**
		 * Ermittelt Schnittpunkte zweier Kreise.
		 * @param coA
		 * @param coB
		 * @param r1
		 * @param r2
		 * @return
		 */
		public static Coordinate[] calc_intersections( Coordinate coA, Coordinate coB, double r1, double r2 )
		{
			double ax = coA.getX();double bx = coB.getX();
			double ay = coA.getY();double by = coB.getY();
			
			
			double d = Util.get_length(ax, ay, bx, by);
			
			double d1 =  ((r1*r1) - (r2*r2) + (d*d))  / (2 * d);
			
			double h = Math.sqrt( Math.abs( (r1*r1) - (d1*d1) ) );
			
			double x3 = ax + ( d1 * (bx - ax) ) / d;
			
			double y3 = ay + ( d1 * (by - ay) ) / d;
			
			double x4_i = x3 + ( h * (by - ay) ) / d;
			
			double y4_i = y3 - (h * (bx - ax)) / d;
			
			double x4_ii = x3 - (h * (by - ay)) / d;
			
			
			double y4_ii = y3 + (h * (bx - ax)) / d;
			
			
			Coordinate[] arr = new Coordinate[2];
			arr[0] = new Coordinate(x4_i,  y4_i);
			arr[1] = new Coordinate(x4_ii, y4_ii);
			
			return arr;
			
		}	
	}
