package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTRedRightGear extends CommandGroup
{
	/* Created and used at MTVernon */
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
	
	public MTRedRightGear(boolean enableCamera, boolean enableShooter)
	{
		this();
		if (enableCamera)
		{
			addSequential(new WaitForGearCommand());
			addSequential(new AlignTowerCommand(.4));
			if (enableShooter)
				addSequential(new SpinUpShooterCommand());
		}
	}
}
