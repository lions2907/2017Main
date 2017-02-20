package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClearIntakeCommand extends Command
{
	public ClearIntakeCommand()
	{
		super("ShooterCommand");
		requires(Robot.shooter);
	}

	protected void initialize()
	{
		Robot.shooter.shift(false);
	}
	
	public void end()
	{
		Robot.shooter.shift(true);
		Robot.shooter.shoot(0);
	//	Robot.shooter.stopTimer();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		Robot.shooter.shoot(.5);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
