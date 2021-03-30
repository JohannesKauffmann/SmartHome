package facades;

/**
 * The Facade is responsible for executing a range of predefined
 * actions at once, on predefined actuators. The specific actions
 * are implemented in the concrete Facades.
 */
public interface Facade
{
	/**
	 * Execute a predefined set of actions on a predefined set
	 * of actuators. 
	 */
	public void doAction();
}
