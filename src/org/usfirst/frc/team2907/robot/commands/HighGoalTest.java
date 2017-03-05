package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HighGoalTest extends CommandGroup
{
	public HighGoalTest()
	{
		addSequential(new ShooterCommand(true));
		addSequential(new AlignTowerCommand(.25));
	}
}
