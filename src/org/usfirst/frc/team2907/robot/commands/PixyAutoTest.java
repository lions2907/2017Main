package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PixyAutoTest extends CommandGroup
{
	public PixyAutoTest()
	{
		addSequential(new AlignPixyCommand());
	}
}
