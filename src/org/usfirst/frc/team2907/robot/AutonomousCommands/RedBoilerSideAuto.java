package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedBoilerSideAuto extends CommandGroup
{
	public RedBoilerSideAuto()
	{
		addSequential(new DriveDistance(8, .5));
		addSequential(new RotateToAngle(5));
		addSequential(new DriveDistance(68, .5));
		addSequential(new DriveDistance(12, .25));
		addSequential(new RotateToAngle(-10));
		addSequential(new DriveDistance(12, .5));
		addSequential(new DriveDistance(12, .25));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(12, .25));
	}
}
