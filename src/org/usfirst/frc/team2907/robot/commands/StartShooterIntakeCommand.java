package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StartShooterIntakeCommand extends Command
{
	public boolean on;

	public StartShooterIntakeCommand(boolean on)
	{
		super("Start Intake Command");
		requires(Robot.shooter);
		this.on = on;
	}

	protected void initialize()
	{
		Robot.shooter.intake(on ? -1 : 0);
	}

	@Override
	protected boolean isFinished()
	{
		return true;
	}

}
