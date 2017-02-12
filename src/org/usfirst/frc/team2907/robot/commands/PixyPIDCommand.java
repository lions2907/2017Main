package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PixyPIDCommand extends Command
{
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	static final double kToleranceDegrees = 2.0f;
	
	private PIDController pidController;
	private PIDOutput output;
	
	public PixyPIDCommand()
	{
		super("PixyPIDCommand");
		requires(Robot.camera);
		requires(Robot.driveTrain);
		
		output = new PIDOutput();
		pidController = new PIDController(kP, kI, kD, Robot.camera, output);
		pidController.setInputRange(0, Camera.IMAGE_WIDTH);
		pidController.setOutputRange(-.5, .5);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		pidController.setContinuous(true);
		
	}
	
	protected void initialize()
	{
		pidController.setSetpoint(Camera.IMAGE_WIDTH / 2);
		pidController.enable();
	}
	
	protected void execute()
	{
	}
	
	protected boolean isFinished()
	{
		return false;
	}
	
	protected void end()
	{
		pidController.disable();
	}
	
	protected void interrupted()
	{
		end();
	}
	
	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{
		
		public void pidWrite(double output)
		{
			Robot.driveTrain.arcadeDrive(0, output);
			//Robot.driveTrain.mechDrive(Robot.oi.leftStick.getX(), Robot.oi.leftStick.getY(), output, Robot.driveTrain.sensorBoard.getAngle());
		}
		
	}
}
