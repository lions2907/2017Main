package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedRightGearAuto extends CommandGroup
{
	public RedRightGearAuto()
	{
		addSequential(new DriveDistance(6, .5));
		addSequential(new RotateToAngle(35));
		addSequential(new DriveDistance(71.5, .75));
		addSequential(new RotateToAngle(-97));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(24, .25));
	}
}
