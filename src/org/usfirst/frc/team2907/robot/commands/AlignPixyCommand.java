package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.AutonomousCommands.Flag;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AlignPixyCommand extends Command
{
	private double OFFSET = 210;
	private double POWER = .4;
	private Flag flag;
	
	public AlignPixyCommand()
	{
		super("AlignPixy");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);
	}
	
	public AlignPixyCommand(Flag flag)
	{
		this();
		flag.angle = Robot.driveTrain.getAngle();
		this.flag = flag;
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
		
		if (flag != null && ((Robot.driveTrain.getAngle() - flag.angle) > flag.failAngle))
		{
			flag.failed = true;
		}
	}

	@Override
	protected boolean isFinished()
	{
		if (flag != null && flag.failed)
		{
			return true;
		}
		
		double offset = Robot.cameraManager.getGearOffset();
		System.out.println("offset : " + offset);
		// TODO Auto-generated method stub
		return Math.abs(offset - OFFSET) < 10;
	}

}
