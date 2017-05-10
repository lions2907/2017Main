package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle.PIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterPIDCommand extends Command
{
	/* controls shooter with velocity control */
	private double rpm;

	public ShooterPIDCommand(double rpm)
	{
		super("ShooterCommand");
		requires(Robot.shooter);
		this.rpm = rpm;
	}

	protected void initialize()
	{
		Robot.shooter.enableSpeedController();
		Robot.shooter.spinUp(3);
	}

	public void end()
	{
		Robot.shooter.stop();
	}

	protected void interrupted()
	{
		end();
	}

	@Override
	public void execute()
	{
		System.out.println("Shooter RPM : " + Robot.shooter.getRPM());
		Robot.shooter.shoot(rpm);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}
}
