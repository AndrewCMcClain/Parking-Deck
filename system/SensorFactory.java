package system;

public class SensorFactory {
	private static Object lockObject = new Object();
	private static SensorManager sm;
	
	protected static SensorManager getSensorManager(){
		if(sm == null){
			synchronized(lockObject){
				if(sm == null){
					sm = new SimulatedSensorManager();
				}
			}
		}
		return sm;
	}
}
