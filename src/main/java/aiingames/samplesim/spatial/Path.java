package aiingames.samplesim.spatial;

import java.util.LinkedList;


public class Path 
{
	public LinkedList<Coordinate> path_points;

	public Path()
	{
		path_points = new LinkedList<Coordinate>();
	}
	
	public void add( Coordinate co )
	{
		path_points.add(co);
	}
	
	// Anstieg zwischen 2 Punkten
	public double get_gradient( Coordinate coA, Coordinate coB )
	{
		// (y2 -y1) geteilt durch (x2 - x1)
		return (coB.getY() - coA.getY() ) / ( coB.getX() - coA.getX() );
	}
	
	// Für die normale einer Geraden den Anstieg anpassen
	public double get_normal_gradient( double gradient )
	{	// normale m = -1/m
		return ( -1.0 / gradient );
	}
	
	/**
	 *  Gibt von einem Punkt coC den kürzesten Vektor zu einer Geraden zwischen Punkt coA und CoB zurück.
	 *  1. Anstieg der normalen zu coA;coB ausrechnen
	 *  2. Diesen Anstieg für eine neue Funktion die durch den Punkt coC gehen soll nehmen und Schnittpunkt mit y
	 *     Achse ausrechnen.
	 *     Ergebnis: Funktion die durch den Punkt coC geht und im rechten Winkel auf coA;coB steht.
	 *  3. Endpunkt für diese Funktion wählen: möglichst groß um coA;coB zu schneiden
	 *  4. Schnittpunkt beider Funktionen berechnen
	 *  5. Vektor zwischen diesem Schnittpunkt und Punkt coC zurückgeben
	 *  		
	 *  	coA
	 *  	 |kürzester weg als vector zu der geraden coA;coB
	 *  	 |-------------------------------------------------- coC
	 *  	 |
	 *  	coB
	 *  	
	 * @param coA Startpunkt gerade a
	 * @param coB Endpunkt   Gerade a
	 * @param coC Punkt B
	 * @return kürzester Vector zwischen Gerade A und Punkt B (NICHT NORMALISIERT)
	 */
	public Coordinate get_shortest_vector_to_path( Coordinate coA, Coordinate coB, Coordinate coC )
	{
		// Anstieg der normalen zwischen 2 Punkten berechnen
		double nM = this.get_normal_gradient( this.get_gradient( coA, coB ) );
		
		// Schnittpunkt mit der y-Achse berechnen für die gerade mit anstieg nM die durch den Punkt coC geht
		double b = coC.getY() - ( nM *coC.getX() );
		
		// start und endpunkt der gerade aufstellen
		// endpunkt sollte größtmöglichst sein, damit die andere gerade auch wirklich geschnitten wird
		double nX2 = 100000;
		double nY1 = b; 
		double nY2 = nM * nX2 + b; 
		
		// ermittelter Schnittpunkt von gerade coA;coB und der eben erechneten normalen
		Coordinate co = this.get_intersection_point( new Coordinate(0.0, nY1) , new Coordinate(nX2, nY2), coA, coB);
		
		// vektor zwischen schnittpunkt und punkt coC
		double vecX = co.getX() - coC.getX();
		double vecY = co.getY() - coC.getY() ;
		
		Coordinate vec = new Coordinate(vecX, vecY);
		
		//double length = Util.get_length(0, 0, vecX, vecY);
		return vec;
		
	}
	
	// Schnittpunkt von 2 Geraden ermitteln
	public Coordinate get_intersection_point( Coordinate coA, Coordinate coB, Coordinate coC, Coordinate coD )
	{
		
		double m1 = this.get_gradient(coA, coB);
		double m2 = this.get_gradient(coC, coD);
		
		// y = m * x + b
		// b = y - m * x 
		
		double b1 = coA.getY() - ( m1 * coA.getX() );
		double b2 = coC.getY() - ( m1 * coC.getX() );
		
		// m1 * x1 + b1 = m2 * x2 + b2
		// 2 * x1 + 2 = 3 * x1 + 5
		
		double temp_right = b2 - b1;
		double temp_left  = m1 - m2;
		
		double ix = temp_right / temp_left;
		double iy = m1 * ix + b1;
		
		
		return new Coordinate(ix,iy);
	}
	
	
	
}
