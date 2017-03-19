package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.DriveDistance.PIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceBack extends Command
{
	double conversionFactor = -33.1375 / 100.5; // ticks per inch
	private double encoderTicks;
	private double startingTicks;
	private double power;
	
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;
	
	private PIDController pidController;
	private PIDOutput output;
	
	private double lastOutput;
	
	public DriveDistanceBack(double inches, double power)
	{
		super();
		requires(Robot.driveTrain);
		encoderTicks = inches * conversionFactor;
		this.power = power;
	}
	
	@Override
	public void initialize()
	{
		Robot.driveTrain.getSensorBoard().reset();
		
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);
		
		output = new PIDOutput();
		pidController = new PIDController(kP, kI, kD, Robot.driveTrain.getSensorBoard(), output);
		pidController.setInputRange(-180, 180);
		pidController.setOutputRange(-.1, .1);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		pidController.setContinuous(true);
		
		pidController.setSetpoint(0);
		pidController.enable();
		
		Robot.driveTrain.resetDistance();
		startingTicks = Robot.driveTrain.getRightDistance();
		System.out.println("Starting ticks : " + startingTicks);
	}
	
	public void end()
	{
		pidController.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	public void execute()
	{
		//Robot.driveTrain.arcadeDrive(0.5, lastOutput);
		System.out.println("distance : " + Robot.driveTrain.getRightDistance() + " goal ticks : " + (encoderTicks + startingTicks));
	}

	@Override
	protected boolean isFinished()
	{
		return Math.abs(Robot.driveTrain.getRightDistance()) >= Math.abs(encoderTicks + startingTicks);
	}
	
	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{

		public void pidWrite(double output) {
			lastOutput = -output;
			System.out.println("angle : " + Robot.driveTrain.getAngle() + ", output : " + output);
			Robot.driveTrain.arcadeDrive(power, lastOutput);
//			Robot.driveTrain.arcadeDrive(0, -output);
		}
		
	}
}
