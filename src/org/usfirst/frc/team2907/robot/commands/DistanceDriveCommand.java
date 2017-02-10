package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DistanceDriveCommand extends Command {
	private double distance;
	private double speed;

	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kToleranceDegrees = 2.0f;

	private PIDController pidController;
	private PIDOutput output;

	public DistanceDriveCommand(double distance, double speed) {
		super("DistanceDrive");
		requires(Robot.driveTrain);
		this.distance = distance;
		this.speed = speed;

		if (Robot.driveTrain.isNavigationAvaliable()) {
			output = new PIDOutput();
			pidController = new PIDController(kP, kI, kD,
					Robot.driveTrain.getSensorBoard(), output);
			pidController.setInputRange(-180, 180);
			pidController.setOutputRange(-.5, .5);
			pidController.setAbsoluteTolerance(kToleranceDegrees);
			pidController.setContinuous(true);

		}
	}

	protected void initialize() {
		Robot.driveTrain.resetDistance();
		if (Robot.driveTrain.isNavigationAvaliable()) {
			pidController.setSetpoint(Robot.driveTrain.getAngle()); // don't drift
			pidController.enable();
		}
	}

	protected void execute() {
		if (!Robot.driveTrain.isNavigationAvaliable()) {
			// navigation board not available, drive without according for drift
			Robot.driveTrain.arcadeDrive(speed, 0);
		}
	}

	protected boolean isFinished() {
		return Robot.driveTrain.getDistance() < distance;
	}

	protected void end() {
		if (Robot.driveTrain.isNavigationAvaliable())
			pidController.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}

	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput {

		public void pidWrite(double output) {
			Robot.driveTrain.arcadeDrive(speed, output);
		}

	}
}
