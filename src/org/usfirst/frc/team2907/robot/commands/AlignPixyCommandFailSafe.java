package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.AutonomousCommands.Flag;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignPixyCommandFailSafe extends CommandGroup
{
	public AlignPixyCommandFailSafe()
	{
		Flag flag = new Flag();						// flag variable to return state of robot after command ends
		flag.failAngle = 20;
		addSequential(new AlignPixyCommand(flag));
		if (flag.failed)							// likely comms failure with camera (unplugged...)
		{
			addSequential(new RotateToAngle(flag));	//recenter self with gyro
		}
	}
}