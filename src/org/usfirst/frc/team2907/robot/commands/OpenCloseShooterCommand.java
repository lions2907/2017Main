package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenCloseShooterCommand extends Command
{

	public OpenCloseShooterCommand()
	{
		super("Open Close Shooter");
		requires(Robot.shooter);
	}

	@Override
	protected void initialize()
	{
		Robot.shooter.startTimer();
	}

	@Override
	protected void end()
	{
		Robot.shooter.stopTimer();
	}

//	@Override
//	protected void interupted()
//	{
//		
//	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
