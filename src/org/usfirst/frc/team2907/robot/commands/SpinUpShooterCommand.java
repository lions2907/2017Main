package org.usfirst.frc.team2907.robot.commands;
import java.util.Timer;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SpinUpShooterCommand extends Command
{
	private double power;
	public SpinUpShooterCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		Robot.shooter.disableSpeedController();
		power = Preferences.getInstance().getDouble("ShooterPower", .85);
		Robot.shooter.spinUp(3);
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
		System.out.println("Shooter velocity : " + Robot.shooter.getRPM());
		Robot.shooter.shoot(power);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
