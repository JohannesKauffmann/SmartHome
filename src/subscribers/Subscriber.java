package subscribers;

import sensors.Sensor;

/**
 * 
 * Interface declaration of methods that the subscribers in the observer pattern must implement.
 *
 */
public interface Subscriber
{
	public void update(Sensor publisher, int value);
}
