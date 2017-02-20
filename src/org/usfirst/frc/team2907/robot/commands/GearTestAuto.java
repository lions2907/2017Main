package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearTestAuto extends CommandGroup
{
	public GearTestAuto()
	{
		addSequential(new DriveDistance(192));
		//addSequential(new RotateToAngle(-45));
	}
}
