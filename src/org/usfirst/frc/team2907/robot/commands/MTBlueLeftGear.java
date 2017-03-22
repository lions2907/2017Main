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
		addSequential(new DriveDistance(66, .5));
		addSequential(new RotateToAngle(45));
		addSequential(new DriveDistance(32, .5));
		addSequential(new DriveDistance(12, .25));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new AlignTowerCommand(.40, true));
		addSequential(new SpinUpShooterCommand());
	}
}
