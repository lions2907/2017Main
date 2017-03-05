package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command
{
	private boolean on;
	public ShooterCommand(boolean on)
	{
		super("ShooterCommand");
		requires(Robot.shooter);
		this.on = on;
	}

	protected void initialize()
	{
		Robot.shooter.shoot(on);
	}

	@Override
	protected boolean isFinished()
	{
		return true;
	}
}
