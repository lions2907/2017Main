package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGearAuto extends CommandGroup
{
	// THE CONSISTANT STRAIGHT GEAR AUTO MODE, DISTANCES ARE IN INCHES
	public CenterGearAuto()
	{
		addSequential(new DriveDistance(56, .75));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
	}
}
