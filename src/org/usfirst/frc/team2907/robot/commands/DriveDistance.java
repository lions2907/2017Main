package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle.PIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command
{
	/* conversion factor for ticks per inch */
	protected double conversionFactor = -33.1375 / 100.5;
	/* encoder ticks */
	protected double encoderTicks;
	/* starting ticks */
	protected double startingTicks;
	protected double power;
	/* anti-drift pid variables */
	static double kP = 0.08;
	static double kI = 0.00;
	static double kD = 0.00;
	static final double kToleranceDegrees = 1.0f;
	/* pid controllers */
	private PIDController pidController;
	private PIDOutput output;
	
	private double lastOutput;
	
	public DriveDistance(double inches, double power)
	{
		super("DriveDistance");
		requires(Robot.driveTrain);
		/* apply conversionFactor to given distance */
		encoderTicks = inches * conversionFactor;
		this.power = power;
	}
	
	@Override
	public void initialize()
	{
		/* reset angle on navigation board */
		Robot.driveTrain.getSensorBoard().reset();
		/* get variables from dashboard */
		kP = Preferences.getInstance().getDouble("kP", .08);
		kI = Preferences.getInstance().getDouble("kI", 0);
		kD = Preferences.getInstance().getDouble("kD", 0);
		/* init pid output wrapper class */
		output = new PIDOutput();
		/* NavX magnetometer/gyro angle is used as pid source for controller */
		pidController = new PIDController(kP, kI, kD, Robot.driveTrain.getSensorBoard(), output);
		/* NavX angle reads from -180 to 180 rather than 360 */
		pidController.setInputRange(-180, 180);
		/* slow angle controller while driving straight */
		pidController.setOutputRange(-.1, .1);
		pidController.setAbsoluteTolerance(kToleranceDegrees);
		/* run entire time driving straight */
		pidController.setContinuous(true);
		/* set pidController setpoint to zero angle-no drift */
		pidController.setSetpoint(0);
		/* enable pid controller */
		pidController.enable();
		/* reset encoder distance */
		Robot.driveTrain.resetDistance();
		/* at the time, only right encoder was being read */
		startingTicks = Robot.driveTrain.getRightDistance();
		/* print starting ticks, should be zero */
		System.out.println("Starting ticks : " + startingTicks);
	}
	
	public void end()
	{
		pidController.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	public void execute()
	{
		/* print ticks, driving is done in pid output class */
		System.out.println("distance : " + Robot.driveTrain.getRightDistance() + " goal ticks : " + (encoderTicks + startingTicks));
	}

	@Override
	protected boolean isFinished()
	{
		/* end command when goal ticks are reached */
		return Robot.driveTrain.getRightDistance() <= encoderTicks + startingTicks;
	}
	
	class PIDOutput implements edu.wpi.first.wpilibj.PIDOutput
	{

		public void pidWrite(double output)
		{
			lastOutput = -output;
			// output current angle and pid controller output
			System.out.println("angle : " + Robot.driveTrain.getAngle() + ", output : " + output);
			/* drive robot with pid controller angle */
			Robot.driveTrain.arcadeDrive(power, lastOutput);
		}
		
	}

}
