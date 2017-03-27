package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends Command {

	// THIS COMMAND ROTATES TO A GIVEN ANGLE -360-360 VIA PID
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;
	
	private PIDController pidController;
	private PIDOutput output;
	
	private double destDegrees;

	public RotateToAngle(double degrees) {
		super("RotateToAngle");
		requires(Robot.driveTrain);
		
		destDegrees = degrees;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.getSensorBoard().reset();
		
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);
		
		output = new PIDOutput();
		pidController = new PIDController(kP, kI, kD, Robot.driveTrain.getSensorBoard(), output);
		pidController.setInputRange(-180, 180);
		pidController.setOutputRange(-.5, .5);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		pidController.setContinuous(true);
		
		pidController.setSetpoint(destDegrees);
		pidController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pidController.onTarget() || (Math.abs(Robot.driveTrain.getAngle() - destDegrees) <= 1);
	}

	// Called once after isFinished returns true
	protected void end() {
		pidController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
	
	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{

		public void pidWrite(double output) {
			System.out.println("angle : " + Robot.driveTrain.getAngle());
			Robot.driveTrain.arcadeDrive(0, -output);
		}
		
	}
}
