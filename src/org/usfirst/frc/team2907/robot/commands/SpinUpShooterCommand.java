package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class SpinUpShooterCommand extends Command
{
	/* This class controls the shooter in default mode (PercentVBus) */
	private double power;
	public SpinUpShooterCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		/* get variables from dashboard */
		double spinUpTime = Preferences.getInstance().getDouble("SpinUpTime", 2);
		Robot.shooter.setSpinUpTime(spinUpTime);
		Robot.shooter.disableSpeedController();
		power = Preferences.getInstance().getDouble("ShooterPower", .85);
		Robot.shooter.spinUp(spinUpTime);
	}
	
	public void end()
	{
		Robot.shooter.stop();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		/* log shooter velocity */
		System.out.println("Shooter velocity : " + Robot.shooter.getRPM());
		Robot.shooter.shoot(power);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
