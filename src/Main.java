import java.io.InputStreamReader;
import java.util.Scanner;

import actuators.*;
import commands.*;
import facades.*;
import sensors.*;
import subscribers.Phone;

/**
 * This class implements the logic for the console application. It will
 * initialize a new made Controller and start the read line methods.
 */
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

	/**
	 * This method is the start point for the console application. Based on the
	 * input it will call another method.
	 */
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
				controller.printActuators();
				break;
			case "-actuatorScreen":
				actuatorScreen();
				break;
			case "-printFacades":
				controller.printFacades();
				break;
			case "-executeFacade":
				executeFacade();
				break;
			case "-printSensors":
				controller.printSensors();
				break;
			case "-doMeasurement":
				doMeasurement();
			default:
				break;
			}
		}
	}

	/*
	 * Prints the help message for the home screen. It lists all available commands.
	 */
	private static void printHelpMessage()
	{
		System.out.println("\nWelcome to your SmartHome!\n");

		System.out.println("The following commands are available to use:");

		System.out.format("%1$-15s  =>  %2$-40s", "-help", "Prints this message.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-printActuators", "Prints a list of available actuators.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-actuatorScreen", "Pick an actuator and perform an action.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-printFacades", "Prints a list of available facades.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-executeFacade", "Pick and execute a facade.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-printSensors", "Prints a list of available sensors.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-doMeasurement",
				"Pick a sensor and force it to measure for a single interval.");
		System.out.println();

		System.out.println("\nReturning to home screen...\n");
	}

	/**
	 * This method will create a 'screen' in the command line application. The user
	 * is able to put in a name. The corresponding face will be executed.
	 */
	private static void executeFacade()
	{
		while (true)
		{
			System.out.println("Select a facade by name. Press q to return");
			String input = scanner.nextLine();
			if (input.equals("q"))
			{
				return;
			}
			if (controller.executeFacade(input))
			{
				System.out.println("Executed Facade");
			} else
			{
				System.err.println("Facade '" + input.toString() + "' not found.");
			}
		}
	}

	/**
	 * This method give the user the possibility to let a sensor measure a new
	 * value. The user can give the name of the sensor, than the corresponding
	 * sensor will do a measurement.
	 */
	private static void doMeasurement()
	{
		while (true)
		{
			System.out.println("Select a sensor by name. Press q to return.");

			String input = scanner.nextLine();
			if (input.equals("q"))
			{
				return;
			}
			Sensor sensor = controller.getSensor(input);
			if (sensor != null)
			{
				System.out.println("Measuring:\n");
				sensor.doMeasurement();
			} else
			{
				System.err.println("Sensor '" + input.toString() + "' not found.");
			}
		}
	}

	/**
	 * This method generates the 'actuator screen'. Multiple action are possible on
	 * an actuator(wrapper). Therefore, a new screen is created with the possibility
	 * to do actions on the actuator.
	 */
	private static void actuatorScreen()
	{
		while (true)
		{
			System.out.println("Select an actuator by name. Press q to return");
			String input = scanner.nextLine();

			if (input.equals("q"))
			{
				return;
			}

			ActuatorWrapper actuatorWrapper = controller.getActuator(input);
			if (actuatorWrapper != null)
			{
				System.out.println("Selected actuator: " + input);
				doActuatorAction(actuatorWrapper);
			} else
			{
				System.err.println("Actuator '" + input.toString() + "' not found.");
			}
		}

	}

	/**
	 * This method bundles all the different operations that can be done on an actuator(Wrapper).
	 * @param actuatorWrapper
	 */
	private static void doActuatorAction(ActuatorWrapper actuatorWrapper)
	{
		while (true)
		{
			System.out.println(
					"Do an action on selected actuator by executing a command. Use -help to view the available commands or press q to quit.");
			String input = scanner.nextLine();
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
				if (actuatorWrapper.undo())
				{
					System.out.println("Undoed selected actuator");
				} else
				{
					System.err.println("This actuator doesn't have a history yet.");
				}
				break;
			case "-printCommands":
				System.out.println("Commands:");
				actuatorWrapper.printCommands();
				break;
			case "-executeCommand":
				executeCommand(actuatorWrapper);
				break;
			default:
				break;
			}
		}
	}

	/*
	 * Prints all possible commands in the actuator screen.
	 */
	private static void actuatorScreenHelp()
	{
		System.out.println("\nActuator screen help!\n");

		System.out.println("The following commands can be used in the actuator screen:");

		System.out.format("%1$-15s  =>  %2$-40s", "-help", "Prints this message.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-undo", "Revert this actuator to its previous state.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-printCommands",
				"Prints a list of commands which can be executed on this actuator.");
		System.out.println();
		System.out.format("%1$-15s  =>  %2$-40s", "-executeCommand", "Pick a command and excute it.");
		System.out.println();

		System.out.println("\nReturning to actuator screen...\n");
	}

	/**
	 * This method gives the user the possibility to execute an action on the before selected actuator.
	 * This method will get a command by name, then it will save the state of the actuator(Memento pattern).
	 * After the saving of the state, the command will be executed.
	 * @param actuatorWrapper
	 */
	private static void executeCommand(ActuatorWrapper actuatorWrapper)
	{
		while (true)
		{
			System.out.println("Execute a command by name.");
			System.out.println("Type the name of the command to execute or press q to quit.");
			String input = scanner.nextLine();
			if (input.equals("q"))
			{
				break;
			}
			Command command = actuatorWrapper.getCommand(input);
			if (command != null)
			{
				actuatorWrapper.saveState();
				command.execute();
				System.out.println("Command executed!");
			} else
			{
				System.err.println("Command with name '" + input.toString() + "' not found.");
			}
		}

	}

	/*
	 * Method for initializing the sensors, facades, commands, actuators, etc.
	 */
	private static void initialize(Controller controller)
	{
		System.out.println("Start initializing controller");

		Phone phone = new Phone("phony phone");

		// initialize sensors
		Sensor tempSensor = new TemperatureSensor("Sensornator Great Temperature");
		Sensor humSensor = new HumiditySensor("GDPR NON CompliAnt 9000 Hummy Sensor");

		// add sensors
		controller.addSensor(tempSensor);
		controller.addSensor(humSensor);

		// phony phone subscribes to temperature sensor.
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
		Facade heatingFacade = new HeatingFacade(heaterWrapper, fanWrapper, aircoWrapper, sprinklerWrapper);
		Facade coolingFacade = new CoolingFacade(heaterWrapper, fanWrapper, aircoWrapper);
		Facade dryingFacade = new DryingFacade(heaterWrapper, fanWrapper, aircoWrapper);
		Facade humidfyFacade = new HumidifyFacade(heaterWrapper, fanWrapper, aircoWrapper, sprinklerWrapper);

		// add all facades to the application
		controller.addFacade("heating", heatingFacade);
		controller.addFacade("cooling", coolingFacade);
		controller.addFacade("drying", dryingFacade);
		controller.addFacade("humidifying", humidfyFacade);

		System.out.println("Finished initializing controller");
	}
}
