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
	/* alignment coordinates */
	public static final int HORIZONTAL_OFFSET = 200;
	public static int VERTICAL_OFFSET = 90;
	/* allowed horizontal error */
	public static final int HORIZONTAL_ERROR = 10;
	/* allowed vertical error */
	public static final int VERTICAL_ERROR = 10;
	/* alignment flags */
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
		alignedVertical = false;

		double horizontalOffset = Robot.cameraManager.getTowerXOffset();
		if (horizontalOffset - HORIZONTAL_OFFSET > HORIZONTAL_ERROR)
		{
			alignedHorizontal = false;
		}
	}

	protected void execute()
	{
		/* read cameras*/
		Robot.cameraManager.readCameras();
		/* simple if statements move robot to specified coordinates */
		/* pixy is fast enough that pre-calculation isn't really needed */
		if (!alignedHorizontal)
		{
			/* get horizontal offset */
			double horizontalOffset = Robot.cameraManager.getTowerXOffset();
			/* check if its within the allowed error */
			if (Math.abs(horizontalOffset - HORIZONTAL_OFFSET) < HORIZONTAL_ERROR)
			{
				/* if so stop and set flag to true */
				Robot.driveTrain.arcadeDrive(0, 0);
				alignedHorizontal = true;
			} else
			{
				/* not aligned, turn towards target */
				Robot.driveTrain.arcadeDrive(0, (horizontalOffset - HORIZONTAL_OFFSET > 0) ? -power : power);
			}
		} else if (alignedHorizontal && !alignedVertical) /* if aligned horizontal, align vertical */
		{
			/* get vertical offset */
			double verticalOffset = Robot.cameraManager.getTowerYOffset();
			/* check if its within allowed error */
			if (Math.abs(verticalOffset - VERTICAL_OFFSET) < VERTICAL_ERROR)
			{
				alignedVertical = true;
				Robot.driveTrain.arcadeDrive(0, 0);
			} else
			{
				/* drive forwards or backwards towards tower */
				Robot.driveTrain.arcadeDrive((verticalOffset - VERTICAL_OFFSET > 0) ? -power : power, 0);
			}
		}
	}

	protected boolean isFinished()
	{
		if (alignedHorizontal && alignedVertical) /* were aligned with tower! */
		{
			System.out.println("We did it!");
			Robot.cameraManager.aligned = true;
		}
		return alignedHorizontal && alignedVertical;
	}

	protected void end()
	{
		Robot.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted()
	{
		end();
	}
}
