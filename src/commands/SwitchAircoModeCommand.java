package commands;

import actuators.Airco;
import actuators.AircoModus;

/**
 * The SwitchAircoModeCommand is a concrete Command.
 */
public class SwitchAircoModeCommand implements Command
{
	/**
	 * The receiver is the object which does the actual work
	 */
	private Airco receiver;

	public SwitchAircoModeCommand(Airco receiver)
	{
		this.receiver = receiver;
	}

	/**
	 * The implementation of the execute method from the Command interface
	 */
	@Override
	public void execute()
	{
		AircoModus currentModus = this.receiver.getModus();
		AircoModus newModus = null;
		boolean next = false;

		for (AircoModus modus : AircoModus.values())
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
			newModus = AircoModus.values()[0];
		}

		this.receiver.setModus(newModus);
	}
}
