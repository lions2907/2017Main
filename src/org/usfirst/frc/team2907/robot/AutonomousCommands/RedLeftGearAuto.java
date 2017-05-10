package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedLeftGearAuto extends CommandGroup
{
	/* The original auto created during build season, on our 'practice field' */
	public RedLeftGearAuto()
	{
		addSequential(new DriveDistance(36, .75));
		addSequential(new RotateToAngle(-30));
		addSequential(new DriveDistance(32, .75));
		addSequential(new RotateToAngle(90));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(30, .25));
	}
}
