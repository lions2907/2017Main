package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AlignPixyCommand extends Command
{
	private double OFFSET = 210;
	private static final double power = .4;
	public AlignPixyCommand()
	{
		super("AlignPixy");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
		OFFSET = Preferences.getInstance().getDouble("GearPixelOffset", 210);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.cameraManager.readCameras();
		double offset = Robot.cameraManager.getGearOffset();
		if (offset < OFFSET)
		{
			Robot.driveTrain.arcadeDrive(0, power);
		} else 
		{
			Robot.driveTrain.arcadeDrive(0, -power);
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
