package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class BackUpGearCommand extends Command
{
	private final double conversionFactor = -33.1375 / 100.5; // ticks per inch
	private double distance;
	
	public BackUpGearCommand()
	{
		super("BackUpGearCommand");
		requires(Robot.driveTrain);
		System.out.println("testout");
	}
	
	public void initialize()
	{
		Robot.driveTrain.resetDistance();
		distance = Preferences.getInstance().getDouble("GearBackDistance", 2.0);
	}
	
	public void execute()
	{
		System.out.println("test : " + Robot.driveTrain.getLeftDistance());
		Robot.driveTrain.arcadeDrive(-.5, 0);
	}
	
	public void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
    protected void interrupted() {
    	System.out.println("command canceled");
    	end();
    }
	
	@Override
	protected boolean isFinished()
	{
		System.out.println("test : " + Robot.driveTrain.getLeftDistance() + " end : " + (2.0 * conversionFactor));
		return -Robot.driveTrain.getLeftDistance() >= Math.abs((distance * conversionFactor));
	}

}
