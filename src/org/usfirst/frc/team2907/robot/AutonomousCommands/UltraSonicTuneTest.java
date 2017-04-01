package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.DriveDistanceBack;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class UltraSonicTuneTest extends CommandGroup
{
	public UltraSonicTuneTest()
	{
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistanceBack(12, -.5));
	}
}
