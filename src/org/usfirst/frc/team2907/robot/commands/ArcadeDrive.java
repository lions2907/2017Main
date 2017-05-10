package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import sun.dc.pr.PRError;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command
{
	private double accelX;
	private double accelY;

	public ArcadeDrive()
	{
		super("ArcadeDrive");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		/* drive robot with driver ps4 joystick */
		Robot.driveTrain.arcadeDrive(Robot.oi.driveStick.getAxis(AxisType.kY), -Robot.oi.driveStick.getRawAxis(2));
		/* get accelX and accelY from navX */
		accelX = Math.abs(Robot.driveTrain.getSensorBoard().getWorldLinearAccelX());
		accelY = Math.abs(Robot.driveTrain.getSensorBoard().getWorldLinearAccelY());
		/* if collision rumble joystick */
		Robot.oi.driveStick.setRumble(RumbleType.kLeftRumble, (accelY > .6) ? 1 : 0);
		Robot.oi.driveStick.setRumble(RumbleType.kRightRumble, (accelX > .6) ? 1 : 0);
		/* log drivetrain shifter status to dashboard for drivers */
		SmartDashboard.putString("Drivetrain Gear", (Robot.driveTrain.isHighGear()) ? "HIGH" : "LOW"); 
		/* if logs are enable then print encoder values while driving */
		if (Robot.ENABLE_DRIVE_LOGS)
		{
			System.out.println("Encoder right distance : " + Robot.driveTrain.getRightDistance() + ", left : "
			+ Robot.driveTrain.getLeftDistance());
		}
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
