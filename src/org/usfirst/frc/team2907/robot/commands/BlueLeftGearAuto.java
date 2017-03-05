package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueLeftGearAuto extends CommandGroup
{
	public BlueLeftGearAuto()
	{
		addSequential(new DriveDistance(6, .5));
		addSequential(new RotateToAngle(35));
		addSequential(new DriveDistance(71.5, .75));
		addSequential(new RotateToAngle(-97));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(30, .25));
	}
}
