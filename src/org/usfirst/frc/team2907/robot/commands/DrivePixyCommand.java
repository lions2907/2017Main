package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.CameraManager;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePixyCommand extends Command
{
	public static final double PERCENTAGE = .8;
	private static final double TURN_POWER = .1;
	private double OFFSET = 210;
	private double power;
	private double offsetPower;
	private double targetArea;

	public DrivePixyCommand(double power)
	{
		super("DrivePixyCommand");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);

		this.power = power;
		System.out.println("Init");
	}

	protected void initialize()
	{
		OFFSET = Preferences.getInstance().getDouble("GearPixelOffset", 210);
		targetArea = Robot.cameraManager.getGearArea();
		if (targetArea == 0)
			cancel();
	}

	protected void execute()
	{
		targetArea = Robot.cameraManager.getGearArea();
		System.out.println("area : " + targetArea);
		if (Robot.cameraManager.getGearOffset() < OFFSET)
		{
			offsetPower = TURN_POWER;
		} else
		{
			offsetPower = -TURN_POWER;
		}

		// if (targetArea < CameraManager.IMAGE_WIDTH * PERCENTAGE)
		// {
		Robot.driveTrain.arcadeDrive(power, 0);
		// }
	}

	protected boolean isFinished()
	{
		return targetArea >= (CameraManager.IMAGE_WIDTH * CameraManager.IMAGE_HEIGHT) * PERCENTAGE;
	}

	protected void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted()
	{
	}
}
