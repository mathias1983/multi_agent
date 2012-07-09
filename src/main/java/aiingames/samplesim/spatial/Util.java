package aiingames.samplesim.spatial;

public class Util 
{
	public static double get_length( double x1, double y1, double x2, double y2  )
	{
		return Math.sqrt( (x1*x1+y1*y1+x2*x2+y2*y2) );
	}
	
	public static double to_degree( double x, double y )
	{
		if( y < 0 && x > 0) // 1
  		{
  			return Math.toDegrees(  -Math.toRadians(90) + Math.acos( y ) );
  			
  		}
  		
  		if(y < 0 && x < 0) // 2
  		{
  			return  270.0 - Math.toDegrees(  Math.acos( y ) );
  			
  		}
  		
  		if( y > 0 && x < 0) 
  		{
  			return  270.0 - Math.toDegrees(  Math.acos( y ) );
  			
  		}
  		if( y > 0 && x > 0) 
  		{
  			return 360.0 - Math.toDegrees(  Math.toRadians(90) - Math.acos( y ) );
  		
  		}
  		return 0.0;
	}
	
	public static double[] normalize( double x, double y )
	{
		double abs_value = Math.sqrt( Math.pow(x,2.0) + Math.pow(y,2.0) );

		double[] temp = new double[2];
		temp[0] = x / abs_value;
		temp[1] = y / abs_value;
		
		return temp;
		
	}
	
	public static boolean is_in_range( double sensor_a, double sensor_b, double light )
	{
		
		double temp_a = sensor_a;
		double temp_b = sensor_b;
		double temp_l = light;
		
		double temp_value;
		double temp_light;
		
		if( sensor_b > sensor_a )
		{
			temp_value = 360.0 - sensor_b;
			sensor_b = 0;
			sensor_a += temp_value;
			
			if(  light + temp_value > 360  )
			{
				temp_light = 360.0 - light;
				light = 0;
				light += (temp_value - temp_light);
			}
			else
			{
				light += temp_value;
			}
			 
		}
		
		if( light <= sensor_a && light > sensor_b )
		{
			return true;
		}
		
		return false;
		
		
		
	}
}
