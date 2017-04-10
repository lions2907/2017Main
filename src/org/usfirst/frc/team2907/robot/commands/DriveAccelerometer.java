package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAccelerometer extends DriveDistance
{
	private boolean collisionOccured;
	private double collisionThreshold;
	
	public DriveAccelerometer(double ct, double p)
	{
		super(100, p);
		requires(Robot.driveTrain);
		collisionThreshold = ct;
		collisionOccured = false;
	}
	
	@Override
	public void execute()
	{
		super.execute();
		System.out.println("AccelY : " + Robot.driveTrain.getLinearAccelY() + ", AccelX : " + Robot.driveTrain.getLinearAccelX());
		if (Robot.driveTrain.getLinearAccelX() > collisionThreshold || Robot.driveTrain.getLinearAccelY() > collisionThreshold)
		{
			collisionOccured = true;
		}
	}
	
	@Override
	protected boolean isFinished()
	{
		return collisionOccured;
	}

}
