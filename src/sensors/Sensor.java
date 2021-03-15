package sensors;

import java.util.ArrayList;

import subscribers.Subscriber;

public abstract class Sensor
{
	protected ArrayList<Subscriber> subscribers;
	protected String name;
	
	public void subscribe(Subscriber subscriber) {
		this.subscribers.add(subscriber);
	}
	
	public void unsubscribe(Subscriber subscriber) {
		this.subscribers.remove(subscriber);
	}
	
	public abstract void notifySubscribers();
	
	public abstract void doMeasurement();
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Sensor: " + this.name;
	}
	
}
