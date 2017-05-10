package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.DriveDistance.PIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceBack extends DriveDistance
{
	/* This class just passes a negative power and negative end condition to drivedistance class */
	public DriveDistanceBack(double inches, double power)
	{
		super(inches, power);
	}
	
	@Override
	protected boolean isFinished()
	{
		return Math.abs(Robot.driveTrain.getRightDistance()) >= Math.abs(encoderTicks + startingTicks);
	}
	
}
