package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGearAuto extends CommandGroup
{
	// THE CONSISTANT STRAIGHT GEAR AUTO MODE, DISTANCES ARE IN INCHES
	public CenterGearAuto(boolean enableLiftCamera)
	{
		/* drive 50 inches at half power and slow down to 1/4 near end */
		addSequential(new DriveDistance(50, .50));
		addSequential(new DriveDistance(6, .25));
		/* if gear lift camera is enabled, align */
		if (enableLiftCamera)
		{
			addSequential(new AlignPixyCommand());
		}
		/* continue after alignment */
		addSequential(new DriveDistance(16, .25));
	}
}
