import java.io.InputStreamReader;
import java.util.Scanner;

import actuators.*;
import commands.*;
import facades.*;
import sensors.*;
import subscribers.Phone;

public class Main
{
	// create new scanner for reading user input.
	private static Scanner scanner = new Scanner(new InputStreamReader(System.in));
	
	public static void main(String[] args)
	{
		// create new controller and call the initialize method.
		Controller controller = new Controller();
		initialize(controller);

		System.out.println("Controller ready! use -help for available commands.");
		homeCommand();

	}

	private static void homeCommand()
	{
		System.out.println("--HOME--");
		
		String input = scanner.nextLine();

		switch (input)
		{
		case "-help":
			printHelpMessage();
			break;

		default:
			break;
		}
		
		homeCommand();
	}

	private static void printHelpMessage()
	{
		System.out.println("Dit is de help message!");
		System.out.println("Select");
	}

	/*
	 * Method for initializing the sensors, facades, commands, actuators, etc.
	 */
	private static void initialize(Controller controller)
	{
		System.out.println("Start initializing controller");

		Phone phone = new Phone("phony phone");

		// initialize sensors
		Sensor tempSensor = new TemperatureSensor();
		Sensor humSensor = new HumiditySensor();

		// add sensors
		controller.addSensor(tempSensor);
		controller.addSensor(humSensor);

		// phony phone subscibes to temperature sensor.
		tempSensor.subscribe(phone);

		// controller subscribes to hum and temp sensors
		tempSensor.subscribe(controller);
		humSensor.subscribe(controller);

		// initialize some actuators
		Heater heater = new Heater("Hot Heater which loves heating");
		Fan fan = new Fan("Blowing your mind away!");
		Airco airco = new Airco("Nothing is cooler and more attractive than a big comeback, and that'll be me");
		Sprinkler sprinkler = new Sprinkler("I like to make everything wet!");

		// create actuator wrappers
		ActuatorWrapper heaterWrapper = new ActuatorWrapper(heater);
		ActuatorWrapper fanWrapper = new ActuatorWrapper(fan);
		ActuatorWrapper aircoWrapper = new ActuatorWrapper(airco);
		ActuatorWrapper sprinklerWrapper = new ActuatorWrapper(sprinkler);

		// create all different commands:
		Command fanDecrement = new FanDecrementRpmCommand(fan);
		Command fanIncrement = new FanIncrementRpmCommand(fan);
		Command sprinklerOff = new SprinklerOffCommand(sprinkler);
		Command sprinklerOn = new SprinklerOnCommand(sprinkler);
		Command switchAircoMode = new SwitchAircoModeCommand(airco);
		Command switchHeaterMode = new SwitchHeaterModeCommand(heater);

		// add all the commands to appropriate wrappers
		fanWrapper.addCommand("Decrement RPM", fanDecrement);
		fanWrapper.addCommand("Increment RPM", fanIncrement);

		sprinklerWrapper.addCommand("Turn off", sprinklerOff);
		sprinklerWrapper.addCommand("Turn on", sprinklerOn);

		aircoWrapper.addCommand("Switch mode", switchAircoMode);
		heaterWrapper.addCommand("Switch mode", switchHeaterMode);

		// add the actuator wrapper to the application
		controller.addActuator(heaterWrapper);
		controller.addActuator(fanWrapper);
		controller.addActuator(aircoWrapper);
		controller.addActuator(sprinklerWrapper);

		// create and add facades.
		Facade heatingFacade = new HeatingFacade(heater, fan, airco, sprinkler);
		Facade coolingFacade = new CoolingFacade(heater, fan, airco, sprinkler);
		Facade dryingFacade = new DryingFacade(heater, fan, airco, sprinkler);
		Facade humidfyFacade = new HumidifyFacade(heater, fan, airco, sprinkler);

		controller.addFacade("heating", heatingFacade);
		controller.addFacade("cooling", coolingFacade);
		controller.addFacade("drying", dryingFacade);
		controller.addFacade("humidifying", humidfyFacade);

		System.out.println("Finished initializing controller");
	}
}
