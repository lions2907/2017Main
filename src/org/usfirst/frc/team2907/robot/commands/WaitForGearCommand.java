package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitForGearCommand extends Command
{
	public static final double READ_DELAY = .1;
	public static double NO_GEAR_DISTANCE = 1.5; 
	public static double DRIVE_DELAY = 1.5;
	// WAITS FOR GEAR TO TAKEN BY BO SO WE CAN DRIVE OFF
	private boolean finish = false;
	
	public WaitForGearCommand()
	{
		super("WaitForGear");
		requires(Robot.intake);
	}
	
	protected void initialize()
	{
		NO_GEAR_DISTANCE = Preferences.getInstance().getDouble("EmptyGearDistance", 1.5);
		DRIVE_DELAY = Preferences.getInstance().getDouble("UltrasonicDriveDelay", .5);
		finish = false;
	}
	
	public void execute()
	{
		// ULTRA SONIC CAN ONLY BE READ EVERY 100 MS
//		Timer.delay(READ_DELAY);
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
//		System.out.println("ultrasonic distance : " + Robot.intake.getDistance());
//		// IF THE GEAR IS GONE GIVE A DELAY SO ITS CLEAR OF THE ROBOT, I REALLY SHOULD ADD THIS TO THE DASHBOARD
		if (!Robot.intake.isSwitchFlipped())
		{
			finish = true;
			Timer.delay(DRIVE_DELAY);
			System.out.println("Finished");
		}
		return finish;
	}

}
