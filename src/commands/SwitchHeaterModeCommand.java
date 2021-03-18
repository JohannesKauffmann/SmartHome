package commands;

import actuators.Heater;
import actuators.HeaterModus;

public class SwitchHeaterModeCommand implements Command
{
	private Heater receiver;

	public SwitchHeaterModeCommand(Heater heater)
	{
		this.receiver = heater;
	}
	
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
