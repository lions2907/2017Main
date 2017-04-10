package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.AlignPixyCommandFailSafe;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGearAuto extends CommandGroup
{
	// THE CONSISTANT STRAIGHT GEAR AUTO MODE, DISTANCES ARE IN INCHES
	public CenterGearAuto(boolean enableLiftCamera)
	{
		addSequential(new DriveDistance(50, .50));
		addSequential(new DriveDistance(6, .25));
		if (enableLiftCamera)
		{
			addSequential(new AlignPixyCommandFailSafe());
		}
		addSequential(new DriveDistance(16, .25));
		
	}
}
