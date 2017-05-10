package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AlignPixyCommand extends Command
{
	/* default offset */
	private double OFFSET = 210;
	/* default allowed error */
	private double ALLOWED_ERROR = 10;
	/* default power */
	private double POWER = .4;
	
	public AlignPixyCommand()
	{
		super("AlignPixy");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);
	}
	
	protected void initialize()
	{
		/* get variables from dashboard */
		POWER = Preferences.getInstance().getDouble("GearCameraPower", .4);
		OFFSET = Preferences.getInstance().getDouble("GearPixelOffset", 210);
	}

	protected void execute()
	{
		/* read cameras */
		Robot.cameraManager.readCameras();
		// get gear lift camera offset 
		double offset = Robot.cameraManager.getGearOffset();
		// simple if statement to turn robot towards target
		// , pixy reads fast enough (50-60fps) that pre-calculation isn't really needed
		if (offset < OFFSET)
		{
			Robot.driveTrain.arcadeDrive(0, POWER);
		} else 
		{
			Robot.driveTrain.arcadeDrive(0, -POWER);
		}
		System.out.println("offset : " + offset);
	}
	
	public void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	public void interruped()
	{
		end();
	}

	@Override
	protected boolean isFinished()
	{	
		double offset = Robot.cameraManager.getGearOffset();
		System.out.println("offset : " + offset);
		/* end command when on target */
		return Math.abs(offset - OFFSET) < ALLOWED_ERROR;
	}

}
