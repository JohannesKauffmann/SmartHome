package commands;

import actuators.Heater;
import actuators.HeaterModus;

/**
 * The SwitchHeaterModeCommand is a concrete Command.
 */
public class SwitchHeaterModeCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Heater receiver;

	public SwitchHeaterModeCommand(Heater heater)
	{
		this.receiver = heater;
	}

	/**
	 * The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		HeaterModus currentModus = this.receiver.getModus();
		HeaterModus newModus = null;
		boolean next = false;

		for (HeaterModus modus : HeaterModus.values())
		{
			if (next)
			{
				newModus = modus;
				break;
			}

			next = (modus == currentModus);
		}

		if (newModus == null)
		{
			newModus = HeaterModus.values()[0];
		}

		this.receiver.setModus(newModus);
	}

}
