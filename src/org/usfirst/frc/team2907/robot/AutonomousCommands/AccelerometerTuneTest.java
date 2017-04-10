package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.DriveAccelerometer;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AccelerometerTuneTest extends CommandGroup
{
	public AccelerometerTuneTest()
	{
		addSequential(new DriveAccelerometer(.1, .25));
	}
}
