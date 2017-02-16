package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearTestAuto extends CommandGroup
{
	public GearTestAuto()
	{
		addSequential(new DistanceDriveCommand(12 * 2, -.35));
//		addSequential(new RotateToAngle(30));
//		addSequential(new PixyPIDCommand());
	}
}
