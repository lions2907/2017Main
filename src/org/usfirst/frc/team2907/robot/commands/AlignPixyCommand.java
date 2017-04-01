package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AlignPixyCommand extends Command
{
	private double OFFSET = 210;
	private double POWER = .4;
	
	public AlignPixyCommand()
	{
		super("AlignPixy");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		POWER = Preferences.getInstance().getDouble("GearCameraPower", .4);
		OFFSET = Preferences.getInstance().getDouble("GearPixelOffset", 210);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.cameraManager.readCameras();
		double offset = Robot.cameraManager.getGearOffset();
		if (offset < OFFSET)
		{
			Robot.driveTrain.arcadeDrive(0, POWER);
		} else 
		{
			Robot.driveTrain.arcadeDrive(0, -POWER);
		}
		System.out.println("offset : " + offset);
		
	}

	@Override
	protected boolean isFinished()
	{
		double offset = Robot.cameraManager.getGearOffset();
		System.out.println("offset : " + offset);
		// TODO Auto-generated method stub
		return Math.abs(offset - OFFSET) < 10;
	}

}
