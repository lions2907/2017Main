package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenGearIntakeCommand extends Command
{
	/* This command just flips the gear collector to its opposite state */
	public OpenGearIntakeCommand()
	{
		super();
		requires(Robot.intake);
	}
	
	public void initialize()
	{
		Robot.intake.open(!Robot.intake.isOut());
	}
	
	@Override
	protected boolean isFinished()
	{
		return true;
	}

}
