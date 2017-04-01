package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
 
public class PixyTuneTest extends CommandGroup
{
	public PixyTuneTest()
	{
		addSequential(new AlignPixyCommand());
	}
}
