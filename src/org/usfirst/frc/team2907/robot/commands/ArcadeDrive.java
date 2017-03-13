package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import sun.dc.pr.PRError;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command
{

	private double accelX;
	private double accelY;
	private double scaleFactor;

	public ArcadeDrive()
	{
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		scaleFactor = Preferences.getInstance().getDouble("Drive Scale", 1);
	}

	protected void execute()
	{

		if (Robot.oi.maxButton.get())
		{
			Robot.driveTrain.arcadeDrive(
					Robot.oi.driveStick.getAxis(AxisType.kY),
					-Robot.oi.driveStick.getRawAxis(2));// ps4
		} else
		{
			Robot.driveTrain.arcadeDrive(
					Robot.oi.driveStick.getAxis(AxisType.kY) * scaleFactor,
					-Robot.oi.driveStick.getRawAxis(2));// ps4
		}
		// Robot.driveTrain.arcadeDrive(Robot.oi.driveStick.getAxis(AxisType.kY),
		// -Robot.oi.driveStick.getRawAxis(4)); xbox
		accelX = Math.abs(Robot.driveTrain.getSensorBoard()
				.getWorldLinearAccelX());
		accelY = Math.abs(Robot.driveTrain.getSensorBoard()
				.getWorldLinearAccelY());
		 System.out.println("Encoder distance : " +
		 Robot.driveTrain.getRightDistance());
		Robot.oi.driveStick.setRumble(RumbleType.kLeftRumble, (accelY > .6) ? 1
				: 0);
		Robot.oi.driveStick.setRumble(RumbleType.kRightRumble,
				(accelX > .6) ? 1 : 0);
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted()
	{
		end();
	}
}
