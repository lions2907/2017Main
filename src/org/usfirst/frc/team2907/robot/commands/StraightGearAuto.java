package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightGearAuto extends CommandGroup
{
	// THE CONSISTANT STRAIGHT GEAR AUTO MODE, DISTANCES ARE IN INCHES
	public StraightGearAuto()
	{
		addSequential(new DriveDistance(56, .75));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
	}
}
