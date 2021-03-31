package sensors;

import java.util.ArrayList;

import subscribers.Subscriber;

/**
 * 
 * The sensor class sends updates from changes it senses to all subscribers.
 *
 */
public abstract class Sensor
{
	protected ArrayList<Subscriber> subscribers;
	protected String name;

	public Sensor()
	{
		this.subscribers = new ArrayList<Subscriber>();
	}

	/**
	 * Add a subscriber to the notification list.
	 * @param subscriber
	 */
	public void subscribe(Subscriber subscriber)
	{
		this.subscribers.add(subscriber);
	}

	/**
	 * Remove a subscriber from the notification list.
	 * @param subscriber
	 */
	public void unsubscribe(Subscriber subscriber)
	{
		this.subscribers.remove(subscriber);
	}

	/**
	 * Abstract declaration of the notification method.
	 */
	public abstract void notifySubscribers();
	
	/**
	 * Abstract declaration of the measurement method.
	 */
	public abstract void doMeasurement();

	/**
	 * Gets the name of the sensor.
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Sets the name of the sensor.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Sensor: " + this.name;
	}
}
