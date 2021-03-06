package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedLoadingZoneAuto extends CommandGroup
{
	/* Loading zone auto created with measurements at district champs, never tested */
	public RedLoadingZoneAuto()
	{
		addSequential(new DriveDistance(8, .5));
		addSequential(new RotateToAngle(-50));
		addSequential(new DriveDistance(46, .5));
		addSequential(new DriveDistance(20, .25));
		addSequential(new RotateToAngle(42));
		addSequential(new DriveDistance(64, .25));
	}
}
