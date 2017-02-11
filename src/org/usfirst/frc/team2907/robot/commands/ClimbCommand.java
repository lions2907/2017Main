package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command
{

	public ClimbCommand()
	{
		super("ClimbCommand");
		requires(Robot.climber);
	}
	
	protected void execute()
	{
		Robot.climber.climb(1);
	}

	protected void end()
	{
		Robot.climber.climb(0);
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

}