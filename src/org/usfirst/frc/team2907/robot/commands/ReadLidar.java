package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

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
		System.out.println("Lidar distance :" + Robot.camera.read());
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
