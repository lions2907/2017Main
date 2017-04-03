package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightNoCamera extends CommandGroup
{
	public StraightNoCamera()
	{
		addSequential(new DriveDistance(50, .50));
		addSequential(new DriveDistance(12, .25));
//		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
	}
}
