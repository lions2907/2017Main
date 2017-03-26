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
		if (Robot.oi.manipulatorStick.getRawButton(7))
		{
			Robot.intake.move(.5);
		} else if (Robot.oi.manipulatorStick.getRawButton(8))
		{
			Robot.intake.move(-.5);
		} else 
		{
			Robot.intake.move(0);
		}
		
		if (Robot.oi.manipulatorStick.getRawButton(3))
		{
			Robot.intake.moveGear(1);
		}
		//Robot.intake.move(-Robot.oi.manipulatorStick.getRawAxis(5));
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
