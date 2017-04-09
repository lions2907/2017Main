package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle.PIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterPIDCommand extends Command
{
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;

	private PIDController pidController;
	private PIDOutput output;

	private double rpm;

	public ShooterPIDCommand(double rpm)
	{
		super("ShooterCommand");
		requires(Robot.shooter);
		this.rpm = rpm;
	}

	protected void initialize()
	{
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);

		output = new PIDOutput();
		pidController = new PIDController(kP, kI, kD,
				Robot.shooter.RPMPidSource, output);
		pidController.setInputRange(0, 70);
		pidController.setOutputRange(-1, 1);
		pidController.setAbsoluteTolerance(1);
		pidController.setContinuous(true);

		pidController.setSetpoint(rpm);
		pidController.enable();
		Robot.shooter.spinUp(3);
	}

	public void end()
	{
		Robot.shooter.setEnabled(false);
		//		Robot.shooter.shift(false);
		Robot.shooter.intake(0);
		Robot.shooter.shoot(0);
		//		Robot.shooter.stopTimer();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		System.out.println("Shooter RPM : " + Robot.shooter.getRPM()
				+ " distance : " + Robot.shooter.getDistance());
		//		Robot.shooter.shoot(power);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{

		public void pidWrite(double output)
		{
			System.out.println("output : " + output);
			Robot.shooter.shoot(output);
		}

	}
}
