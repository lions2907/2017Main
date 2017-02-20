package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAuto extends CommandGroup
{
	public ShootAuto()
	{
		addSequential(new ShootTimeCommand(5));
	}
}
