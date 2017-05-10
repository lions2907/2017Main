package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTBlueRightGear extends CommandGroup
{
	/* Created and used at MTVernon */
	// ----------Blue right gear-----------
	// - Drive 50 inches
	// - turn -60 degrees
	// - drive 70 inches
	// - align camera
	// - drive 12 inches
	public MTBlueRightGear()
	{
		addSequential(new DriveDistance(50, .5));
		addSequential(new RotateToAngle(-45));
		addSequential(new DriveDistance(70, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
