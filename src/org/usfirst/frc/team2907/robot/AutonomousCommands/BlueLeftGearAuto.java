package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueLeftGearAuto extends CommandGroup
{
	/* Original side auto created during build season, never used */
	public BlueLeftGearAuto()
	{
		addSequential(new DriveDistance(102, .5));
		addSequential(new RotateToAngle(60));
		addSequential(new DriveDistance(6, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
