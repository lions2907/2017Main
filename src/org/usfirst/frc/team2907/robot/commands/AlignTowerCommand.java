package org.usfirst.frc.team2907.robot.commands;

import java.awt.Point;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AlignTowerCommand extends Command
{
	public static int[] OFFSETS = { 0/* feet */, 0/* feet */, 0 /* feet */};
	/*
	 * -------------
	 * ..---- tower x 163.0 tower y 193.0
	 */
	// constants for easy changes
	public static final int HORIZONTAL_OFFSET = 163;
	public static int VERTICAL_OFFSET = 193;
	public static final int HORIZONTAL_ERROR = 10;
	public static final int VERTICAL_ERROR = 10;

	private boolean alignedHorizontal;
	private boolean alignedVertical;

	private double power;

	public AlignTowerCommand(double power)
	{
		super("AlignTowerCommand");
		requires(Robot.driveTrain);
		requires(Robot.cameraManager);
		this.power = power;
	}

	protected void initialize()
	{
		alignedHorizontal = false;
		alignedHorizontal = false;

		double horizontalOffset = Robot.cameraManager.getTowerXOffset();
		if (horizontalOffset - HORIZONTAL_OFFSET > HORIZONTAL_ERROR)
		{
			alignedHorizontal = false;
		}
		
		VERTICAL_OFFSET = findClosestPostition((int)Robot.cameraManager.getTowerYOffset());
	}

	protected void execute()
	{
		Robot.cameraManager.readCameras();

		if (!alignedHorizontal)
		{
			double horizontalOffset = Robot.cameraManager.getTowerXOffset();
			if (Math.abs(horizontalOffset - HORIZONTAL_OFFSET) < HORIZONTAL_ERROR)
			{
				Robot.driveTrain.arcadeDrive(0, 0);
				alignedHorizontal = true;
			} else
			{
				Robot.driveTrain.arcadeDrive(0, (horizontalOffset
						- HORIZONTAL_OFFSET > 0) ? -power : power);
			}
		} else if (alignedHorizontal && !alignedVertical)
		{
			double verticalOffset = Robot.cameraManager.getTowerYOffset();
			if (Math.abs(verticalOffset - VERTICAL_OFFSET) < VERTICAL_ERROR)
			{
				alignedVertical = true;
				Robot.driveTrain.arcadeDrive(0, 0);
			} else
			{
				Robot.driveTrain
						.arcadeDrive(
								(verticalOffset - VERTICAL_OFFSET > 0) ? -power
										: power, 0);
			}
		}
	}

	private int findClosestPostition(int position)
	{
		int smallest = position - OFFSETS[0];
		for (int dis : OFFSETS)
		{
			if (position - dis < smallest)
			{
				smallest = position - dis;
			}
		}
		return smallest;
	}

	protected boolean isFinished()
	{
		if (alignedHorizontal && alignedVertical)
		{
			System.out.println("We did it!");
			Robot.cameraManager.aligned = true;
		}
		return alignedHorizontal && alignedVertical;
	}

	protected void end()
	{
		if (Math.abs(Robot.driveTrain.getAngle()) > 70)
		{
			Robot.cameraManager.aligned = false;
		}
	}

	protected void interrupted()
	{
		end();
	}

	class PIDOutputTurn implements edu.wpi.first.wpilibj.PIDOutput
	{

		public void pidWrite(double output)
		{
			Robot.driveTrain.arcadeDrive(0, -output);
		}

	}
}
