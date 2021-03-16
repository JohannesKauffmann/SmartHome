package subscribers;

import sensors.Sensor;

public interface Subscriber
{
	public void update(Sensor publisher, int value);
}
