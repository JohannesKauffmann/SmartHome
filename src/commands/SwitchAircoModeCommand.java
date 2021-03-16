package commands;

import actuators.Airco;
import actuators.AircoModus;

public class SwitchAircoModeCommand implements Command
{
	private Airco receiver;

	public SwitchAircoModeCommand(Airco receiver)
	{
		this.receiver = receiver;
	}

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
