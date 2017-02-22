package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootTimeCommand extends Command
{
	public ShootTimeCommand(double time)
	{
		super(time);
		requires(Robot.shooter);
	}

	protected void initialize()
	{
//		Robot.shooter.spinUp(2,/ true);
	}
	
	public void end()
	{
//		Robot.shooter.shift(false);
		Robot.shooter.shoot(false);
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
		return isTimedOut();
	}

}
