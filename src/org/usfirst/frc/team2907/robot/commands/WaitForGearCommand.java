package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForGearCommand extends Command
{
	public static double DRIVE_DELAY = 1.5;
	// WAITS FOR GEAR TO TAKEN BY BO SO WE CAN DRIVE OFF
	private boolean isFinished = false;
	
	public WaitForGearCommand()
	{
		super("WaitForGear");
		requires(Robot.intake);
	}
	
	protected void initialize()
	{
		isFinished = false;
	}
	
	public void execute()
	{
	}
	
	public void interrupted()
	{
		end();
	}
	
	public void end()
	{
		
	}
	
	@Override
	protected boolean isFinished()
	{
		/* If the switch is flipped, add delay for pilot to lift gear out */
		if (!Robot.intake.isSwitchFlipped())
		{
			isFinished = true;
			Timer.delay(DRIVE_DELAY);
			System.out.println("Finished");
		}
		return isFinished;
	}

}
