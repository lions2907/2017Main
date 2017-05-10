package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends Command
{
	/* default pid variables */
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;
	/* pid controllers */
	private PIDController pidController;
	private PIDOutput output;

	private double destDegrees;

	public RotateToAngle(double degrees)
	{
		super("RotateToAngle");
		requires(Robot.driveTrain);

		destDegrees = degrees;
	}

	protected void initialize()
	{
		/* reset navx angles */
		Robot.driveTrain.getSensorBoard().reset();
		/* get dashboard variables */
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);

		output = new PIDOutput();
		/* NavX magnetometer/gyro angle is used as pid source for controller */
		pidController = new PIDController(kP, kI, kD, Robot.driveTrain.getSensorBoard(), output);
		/* NavX angle reads from -180 to 180 rather than 360 */
		pidController.setOutputRange(-.5, .5);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		pidController.setContinuous(true);
		// set pid setpoint to given angle
		pidController.setSetpoint(destDegrees);
		pidController.enable();
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return pidController.onTarget() || (Math.abs(Robot.driveTrain.getAngle() - destDegrees) <= 1);
	}

	protected void end()
	{
		pidController.disable();
	}
	protected void interrupted()
	{
		end();
	}

	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{
		public void pidWrite(double output)
		{
			System.out.println("angle : " + Robot.driveTrain.getAngle());
			Robot.driveTrain.arcadeDrive(0, -output);
		}

	}
}
