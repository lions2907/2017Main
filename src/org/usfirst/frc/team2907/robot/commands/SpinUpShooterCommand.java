package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SpinUpShooterCommand extends Command
{
	private boolean status;

	public SpinUpShooterCommand(boolean status)
	{
		super("ShooterCommand");
		requires(Robot.shooter);
		this.status = status;
	}

	protected void initialize()
	{
		Robot.shooter.rumble(true, 2);
		Robot.shooter.rumble(false, 2.2);
	}
	
	public void end()
	{
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		Robot.shooter.shoot(status);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
