package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command
{
	/*This command just shifts the drivetrain to its opposite state */
	public ShiftCommand()
	{
		super("ShiftCommand");
		requires(Robot.driveTrain);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.driveTrain.shift(!Robot.driveTrain.isHighGear());
	}

	protected boolean isFinished()
	{
		return true;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		end();
	}

}
