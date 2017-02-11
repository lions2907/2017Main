package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntakeCommand extends Command
{
	
	public MoveIntakeCommand()
	{
		super("MoveIntake");
		requires(Robot.intake);
	}
	
	public void execute()
	{
		Robot.intake.move(Robot.oi.manipulatorStick.getZ());
	}
	
	public void end()
	{
		Robot.intake.move(0);
	}
	
	public void interrupted()
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
