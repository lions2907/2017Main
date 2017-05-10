package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTBlueLeftGear extends CommandGroup
{
	/* Created and used at MTVernon */
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
	
	public MTBlueLeftGear(boolean enableCamera, boolean enableShooter)
	{
		this();
		if (enableCamera)
		{
			 addSequential(new WaitForGearCommand());
			 addSequential(new AlignTowerCommand(.40));
			 if (enableShooter)
				 addSequential(new SpinUpShooterCommand());
		}
	}
}
