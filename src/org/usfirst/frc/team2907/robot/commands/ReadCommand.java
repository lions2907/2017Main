package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReadCommand extends Command
{
	/* This command is constantly called by the CameraManager subsystem */
	public ReadCommand()
	{
		super("ReadCommand");
		requires(Robot.cameraManager);
	}

	protected void execute()
	{
		Robot.cameraManager.readCameras();
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
