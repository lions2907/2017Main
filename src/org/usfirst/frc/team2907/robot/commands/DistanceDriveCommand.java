package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;

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
		System.out.println("Starting");
		requires(Robot.driveTrain);
		this.distance = distance;
		this.speed = speed;

		if (Robot.driveTrain.isNavigationAvaliable()) {
			output = new PIDOutput();
			pidController = new PIDController(kP, kI, kD,
					Robot.driveTrain.getSensorBoard(), output);
			pidController.setInputRange(-180, 180);
			pidController.setOutputRange(-speed, speed);
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

	protected void execute(){
		System.out.println("encoder : " + Robot.driveTrain.getDistance() + " end condition " +  distance / DriveTrain.DISTANCE_PER_FEET);
// 		Robot.driveTrain.arcadeDrive(speed, 0);
	}

	protected boolean isFinished() {
		return Robot.driveTrain.getDistance() > distance / DriveTrain.DISTANCE_PER_FEET;
	}

	protected void end() {
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
