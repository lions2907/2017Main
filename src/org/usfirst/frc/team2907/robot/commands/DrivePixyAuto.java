package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivePixyAuto extends CommandGroup
{
	public DrivePixyAuto()
	{
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistanceCollision(60, .5));
	}
}
