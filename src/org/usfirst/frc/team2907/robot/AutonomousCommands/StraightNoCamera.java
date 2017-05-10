package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightNoCamera extends CommandGroup
{
	/* Drives straight up to the tower, doesn't use camera */
	public StraightNoCamera()
	{
		addSequential(new DriveDistance(50, .50));
		addSequential(new DriveDistance(12, .25));
		addSequential(new DriveDistance(16, .25));
	}
}
