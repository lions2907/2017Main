package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class ShootTimeCommand extends Command
{
	private double power;
	public ShootTimeCommand(double time)
	{
		super(time);
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		power = Preferences.getInstance().getDouble("ShooterPower", .85);
//		Robot.shooter.intake(-1);
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
		Robot.shooter.shoot(power);
	}

	@Override
	protected boolean isFinished()
	{
		return isTimedOut();
	}

}
