package org.usfirst.frc.team2907.robot.commands;

import java.util.Timer;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SpinUpShooterCommand extends Command
{
	public SpinUpShooterCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		Robot.shooter.spinUp(2, false);
		//Robot.shooter.shift(false);
		//Robot.shooter.startTimer();
//		Robot.shooter.rumble(true, 0.2);
//		Robot.shooter.rumble(false, 2.2);
	}
	
	public void end()
	{
		Robot.shooter.shift(true);
		Robot.shooter.shoot(false);
	//	Robot.shooter.stopTimer();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		Robot.shooter.shoot(true);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
