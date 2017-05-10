package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public abstract class DelayedCallback extends Command
{
	/* abstract class allows a block of code to be called at a delay */
	public DelayedCallback(double delay)
	{
		super(delay);
	}

	public abstract void onCallback();

	protected void initialize()
	{
	}

	protected void execute()
	{
	}

	protected boolean isFinished()
	{
		return isTimedOut();
	}

	protected void end()
	{
		onCallback();
	}

	protected void interrupted()
	{
		end();
	}

}