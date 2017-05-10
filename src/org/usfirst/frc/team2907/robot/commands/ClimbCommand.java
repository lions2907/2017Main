package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command
{
	public static final int JOYSTICK_AXIS = 1; // LEFT AXIS UP-DOWN
	
	public ClimbCommand()
	{
		super("ClimbCommand");
		requires(Robot.climber);
	}
	
	protected void execute()
	{
		/* if guitra is not enabled use ps4 */
		if (!Robot.oi.isGuitarMode())
		{
			if (Robot.oi.manipulatorStick.getY() < 0) // Only drive on way because of locking mechanism
			{
				Robot.climber.climb(Robot.oi.manipulatorStick.getRawAxis(JOYSTICK_AXIS));
			} else 
			{
				Robot.climber.climb(0);
			}
		} else 
		{
			/* guitar not implemented yet */
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
