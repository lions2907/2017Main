package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedLeftGearAuto extends CommandGroup
{
	public RedLeftGearAuto()
	{
		addSequential(new DriveDistance(36, .75));
		addSequential(new RotateToAngle(-30));
		addSequential(new DriveDistance(32, .75));
		addSequential(new RotateToAngle(90));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(30, .25));
	}
}
