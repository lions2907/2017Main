package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForGearCommand extends Command
{
	public static final double NO_GEAR_DISTANCE = 1.5; // HONESTLY NO IDEA WHAT THE ULTRASONIC MEASUREMENTS ARE
	public static final double READ_DELAY = .1;
	public static final double DRIVE_DELAY = 1.5;
	// WAITS FOR GEAR TO TAKEN BY BO SO WE CAN DRIVE OFF
	public WaitForGearCommand()
	{
		super("WaitForGear");
		requires(Robot.intake);
	}
	
	public void execute()
	{
		// ULTRA SONIC CAN ONLY BE READ EVERY 100 MS
		Timer.delay(READ_DELAY);
	}
	
	@Override
	protected boolean isFinished()
	{
		System.out.println("ultrasonic distance : " + Robot.intake.getDistance());
		// IF THE GEAR IS GONE GIVE A DELAY SO ITS CLEAR OF THE ROBOT, I REALLY SHOULD ADD THIS TO THE DASHBOARD
		if (Robot.intake.getDistance() > NO_GEAR_DISTANCE)
		{
			Timer.delay(DRIVE_DELAY);
		}
		System.out.println("Finished");
		return Robot.intake.getDistance() > NO_GEAR_DISTANCE;
	}

}
