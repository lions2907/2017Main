package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForGearCommand extends Command
{
	public WaitForGearCommand()
	{
		super("WaitForGear");
		requires(Robot.intake);
	}
	
	public void execute()
	{
		Timer.delay(.1);
	}
	
	@Override
	protected boolean isFinished()
	{
		return !(Robot.intake.getDistance() < 5);
	}

}
