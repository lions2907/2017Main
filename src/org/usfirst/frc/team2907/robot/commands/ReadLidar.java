package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Lidar;

import edu.wpi.first.wpilibj.command.Command;

public class ReadLidar extends Command
{
	static double distance;
	public ReadLidar()
	{
		super("ReadCommand");
		requires(Robot.lidar);
	}
	
	protected void execute()
	{
//		distance = Robot.lidar.getDistance();
//		if (distance > -1)
//			System.out.println("ultrasonic distance : " + Robot.lidar.getDistance());
		//System.out.println("Lidar distance : " + Robot.lidar.read() / Lidar.CENTIMETERS_TO_FEET);
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
