package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueLeftWallAuto extends CommandGroup
{
	public BlueLeftWallAuto()
	{
		addSequential(new DriveDistance(72, .5));
		addSequential(new RotateToAngle(55));
		addSequential(new DriveDistance(36, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(18, .25));
	}
}
