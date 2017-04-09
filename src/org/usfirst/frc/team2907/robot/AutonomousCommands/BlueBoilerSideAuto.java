package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueBoilerSideAuto extends CommandGroup
{
	public BlueBoilerSideAuto()
	{
		addSequential(new DriveDistance(8, .5));
		addSequential(new RotateToAngle(-15));
		addSequential(new DriveDistance(65, .5));
		addSequential(new DriveDistance(20, .25));
		addSequential(new RotateToAngle(27));
//		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(23, .25));
	}
}
