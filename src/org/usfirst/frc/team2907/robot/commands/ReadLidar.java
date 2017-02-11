package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Lidar;

import edu.wpi.first.wpilibj.command.Command;

public class ReadLidar extends Command
{
	public ReadLidar()
	{
		super("ReadCommand");
		requires(Robot.lidar);
	}
	
	protected void execute()
	{
		System.out.println("Lidar distance : " + Robot.lidar.read() / Lidar.CENTIMETERS_TO_FEET);
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
