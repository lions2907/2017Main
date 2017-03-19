package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTRedRightGear extends CommandGroup
{
	// -----------Red right gear(boiler side)-------------
	// - Drive 43 inches
	// - turn -60 degrees
	// - drive 80 inches
	// - align camera
	// - drive 12 inches
	public MTRedRightGear()
	{
		addSequential(new DriveDistance(50, .5));
		addSequential(new RotateToAngle(-45));
		addSequential(new DriveDistance(57, .5));
		addSequential(new DriveDistance(5, .25));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
