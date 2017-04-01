package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveIntakeCommand extends Command
{
	// THIS COMMAND CONTROLS THE FRONT FUEL INTAKE VIA JOYSTICK INPUT
	public static final int JOYSTICK_AXIS = 5; // RIGHT UP-DOWN ON JOYSTICK AXIS
	public MoveIntakeCommand()
	{
		super("MoveIntake");
		requires(Robot.intake);
	}
	
	public void execute()
	{
		if (!Robot.oi.isGuitarMode())
		{
			Robot.intake.move(-Robot.oi.manipulatorStick.getRawAxis(JOYSTICK_AXIS));
		} else 
		{
			Robot.intake.move(Robot.oi.manipulatorStick.getRawAxis(1));
		}
		
		SmartDashboard.putBoolean("Collector contains gear", Robot.intake.getDistance() > WaitForGearCommand.NO_GEAR_DISTANCE);
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
		return false;
	}

}
