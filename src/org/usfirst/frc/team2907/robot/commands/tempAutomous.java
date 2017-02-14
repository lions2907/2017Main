package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class tempAutomous extends CommandGroup
{
	public tempAutomous()
	{
		addSequential(new DistanceDriveCommand(60, .5));
		addSequential(new RotateToAngle(360));
	}
}
