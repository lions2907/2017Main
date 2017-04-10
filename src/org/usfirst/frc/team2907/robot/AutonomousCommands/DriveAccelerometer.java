package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAccelerometer extends Command
{
	private boolean collisionOccured;
	private double collisionThreshold;
	private double power;
	
	public DriveAccelerometer(double ct, double p)
	{
		super("Drive Accelerometer");
		requires(Robot.driveTrain);
		collisionThreshold = ct;
		power = p;
		collisionOccured = false;
	}
	
	public void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	public void execute()
	{
		System.out.println("AccelY : " + Robot.driveTrain.getLinearAccelY() + ", AccelX : " + Robot.driveTrain.getLinearAccelX());
		Robot.driveTrain.arcadeDrive(power, 0);
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
