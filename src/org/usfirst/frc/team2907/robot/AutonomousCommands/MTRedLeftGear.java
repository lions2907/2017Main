package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTRedLeftGear extends CommandGroup
{
	// ----------Red left gear-------------
	// - Drive 47 inches
	// - turn 60 degrees
	// - drive 70 inches
	// - align camera
	// - drive 12 inches
	public MTRedLeftGear()
	{
		addSequential(new DriveDistance(47, .5));
		addSequential(new RotateToAngle(45));
		addSequential(new DriveDistance(70, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
