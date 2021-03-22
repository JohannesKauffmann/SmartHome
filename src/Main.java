import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
	private static Controller controller;

	public static void main(String[] args)
	{
		// create new controller and call the initialize method.
		controller = new Controller();
		initialize(controller);

		System.out.println("Controller ready! use -help for available commands.");
		homeScreen();

	}

	private static void homeScreen()
	{
		while (true)
		{
			System.out.println("--HOME--");

			String input = scanner.nextLine();

			switch (input)
			{
			case "-help":
				printHelpMessage();
				break;
			case "-printActuators":
				printActuators();
				break;
			case "-ActuatorScreen":
				actuatorScreen();
				break;
			case "-printFacade":
				printFacade();
				break;
			case "-FacadeScreen":
				facadeScreen();
			break;

			default:
				break;
			}
		}
	}
	
	private static void facadeScreen() {
		while(true) {
			System.out.println("Select an facade by name. Press q for return");
			String input = scanner.nextLine();
			if (input.equals("q"))
			{
				return;
			}
			for(Entry<String, Facade> entry : controller.getFacades().entrySet()) {
				if (entry.getKey().equals(input))
				{
					entry.getValue().doAction();
					System.out.println("Executed facade!");
					break;
				}
			}
		}
	}
	
	private static void printFacade() {
		System.out.println("Facades:");
		for (String facadeName : controller.getFacades().keySet())
		{
			System.out.println(facadeName);
		}
	}

	private static void actuatorScreen()
	{
		while (true)
		{
			System.out.println("Select an actuator by name. Press q for return");
			String input = scanner.nextLine();
			if (input.equals("q"))
			{
				return;
			}

			for (ActuatorWrapper actuatorWrapper : controller.getActuators())
			{
				if (actuatorWrapper.getActuator().getName().equals(input))
				{
					System.out.println("Selected actuator: " + input);
					while (true)
					{
						input = scanner.nextLine();
						if (input.equals("q"))
						{
							break;
						}
						switch (input)
						{
						case "-help":
							actuatorScreenHelp();
							break;

						case "-undo":
							actuatorWrapper.undo();
							System.out.println("Undoed selected actuator");
							break;
						case "-printCommands":
							System.out.println("Commands:");
							for (String commandName : actuatorWrapper.getCommands().keySet())
							{
								System.out.println(commandName);
							}
							break;
						case "-executeCommand":
							while (true)
							{
								System.out.println("Execute a command by name:");
								input = scanner.nextLine();
								if (input.equals("q"))
								{
									break;
								}
								for (Entry<String, Command> entry : actuatorWrapper.getCommands().entrySet())
								{
									if (entry.getKey().equals(input))
									{
										entry.getValue().execute();
										System.out.println("Executed command");
										break;
									}

								}
								System.out.println("Command with given name not found: " + input);
							}
							break;

						default:
							break;
						}
					}
				}
				break;
			}

			System.out.println("Actuator not found!");
		}

	}

	private static void actuatorScreenHelp()
	{
		System.out.println();
	}

	private static void printActuators()
	{
		System.out.println("Actuators:");
		for (ActuatorWrapper actuatorWrapper : controller.getActuators())
		{
			System.out.println(actuatorWrapper.getActuator().getName());
		}
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
