package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestDistanceCommand extends CommandGroup
{
	public TestDistanceCommand()
	{
		addSequential(new DistanceDriveCommand(12, 0.5));
	}
}
