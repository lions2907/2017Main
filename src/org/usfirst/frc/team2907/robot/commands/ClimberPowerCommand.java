package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberPowerCommand extends Command
{
	private double power;
	public ClimberPowerCommand(double power)
	{
		super("ClimberPower");
		requires(Robot.climber);
		this.power = power;
	}
	
	public void execute()
	{
		Robot.climber.climb(power);
	}
	
	public void end()
	{
		Robot.climber.climb(0);
	}
	
	public void interrupted()
	{
		end();
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
