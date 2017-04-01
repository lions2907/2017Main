package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command
{
	/* THIS COMMAND CONTROLS THE CLIMBER VIA ANALOG JOYSTICK */
	public static final int JOYSTICK_AXIS = 1; // LEFT AXIS UP-DOWN
	public static final double POWER_MAX = .75; // BEST TORQUE AT 75% POWER?
	
	public ClimbCommand()
	{
		super("ClimbCommand");
		requires(Robot.climber);
	}
	
	protected void execute()
	{
		if (!Robot.oi.isGuitarMode())
		{
			if (Robot.oi.manipulatorStick.getY() < 0) // ONLY DRIVE ONE WAY CAUSE LOCKING MECHANISM
			{
				Robot.climber.climb(Robot.oi.manipulatorStick.getRawAxis(JOYSTICK_AXIS));
				//Robot.climber.climb(-(Math.min(Robot.oi.manipulatorStick.getRawAxis(JOYSTICK_AXIS), POWER_MAX)));
			} else 
			{
				Robot.climber.climb(0);
			}
		} else 
		{
			
		}
	}

	protected void end()
	{
		Robot.climber.climb(0);
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
