package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTBlueLeftGear extends CommandGroup
{
	// -----------Blue left gear(boiler side)-------
	// - drive 46 inches
	// - turn 60 degrees
	// - drive 80 inches
	// - align camera
	// - drive 12 inches
	public MTBlueLeftGear()
	{
		addSequential(new DriveDistance(46, .5));
		addSequential(new RotateToAngle(45));
		addSequential(new DriveDistance(80, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
