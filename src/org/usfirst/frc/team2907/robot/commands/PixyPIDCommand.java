package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle.PIDOutput;
import org.usfirst.frc.team2907.robot.subsystems.Camera;
import org.usfirst.frc.team2907.robot.subsystems.CameraManager;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PixyPIDCommand extends Command
{
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;
	
	private PIDController pidController;
	private PIDOutput output;
	
	public PixyPIDCommand()
	{
		super("PixyPIDCommand");
		requires(Robot.cameraManager);
		requires(Robot.driveTrain);
	}
	
	protected void initialize()
	{
		Robot.driveTrain.getSensorBoard().reset();
		
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);
		
		output = new PIDOutput();
		pidController = new PIDController(kP, kI, kD, Robot.cameraManager.gearCameraPID, output);
		pidController.setInputRange(0, CameraManager.IMAGE_WIDTH);
		pidController.setOutputRange(-.5, .5);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		pidController.setContinuous(true);
		
		pidController.setSetpoint(CameraManager.IMAGE_WIDTH / 2);
		pidController.enable();
	}
	
	protected void execute()
	{
		Robot.cameraManager.readCameras();
	}
	
	protected boolean isFinished()
	{
		return pidController.onTarget();
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
			System.out.println("output : " + -output + " gear pos : " + Robot.cameraManager.getGearOffset());
			Robot.driveTrain.arcadeDrive(0, -output);
		}
		
	}
}
