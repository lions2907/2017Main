package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.ArcadeDrive;
import org.usfirst.frc.team2907.robot.commands.DelayedCallback;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem 
{
	/* maximum amperage allowed on drive talons */
	public static final int MAX_AMPS = 80;
	/* GreyHill encoders pulse 100 times per revolution */
	public static final double ENCODER_PULSES_PR = 100.0;
	// Distance in inches, 4 inch wheels. 
	public static final double DISTANCE_PER_FEET = 4.0 * Math.PI; 
	/* CANTALONS */
	private CANTalon left1 = new CANTalon(RobotMap.TALON_LEFT_1);
	private CANTalon left2 = new CANTalon(RobotMap.TALON_LEFT_2);
	private CANTalon left3 = new CANTalon(RobotMap.TALON_LEFT_3);
	private CANTalon right1 = new CANTalon(RobotMap.TALON_RIGHT_1);
	private CANTalon right2 = new CANTalon(RobotMap.TALON_RIGHT_2);
	private CANTalon right3 = new CANTalon(RobotMap.TALON_RIGHT_3);
	/* DRIVE ENCODERS */
	private Encoder driveEncoderLeft = new Encoder(RobotMap.DRIVE_ENCODER_LCH1, RobotMap.DRIVE_ENCODER_LCH2);
	private Encoder driveEncoderRight = new Encoder(RobotMap.DRIVE_ENCODER_RCH1, RobotMap.DRIVE_ENCODER_RCH2);
	/* SOLONOIDS FOR SHIFTER */
	private Solenoid leftSolenoid = new Solenoid(RobotMap.SOLENOID_DRIVE_1);
	private Solenoid rightSolenoid = new Solenoid(RobotMap.SOLENOID_DRIVE_2);
	private boolean isHighGear;
	/* NAVIGATION BOARD */
	private AHRS sensorBoard;
	private boolean navigationAvaliable;

	public DriveTrain()
	{
		/* setup encoder */
		driveEncoderLeft.setDistancePerPulse((1.0 / ENCODER_PULSES_PR)); // 100 pulses per revolution
		driveEncoderRight.setDistancePerPulse((1.0 / ENCODER_PULSES_PR)); // 100 pulses per revolution
		// setup talons
		/* enable current limit */
		left1.setCurrentLimit(MAX_AMPS);
		left1.EnableCurrentLimit(true);
		/* set to follow main left talon */
		left2.changeControlMode(TalonControlMode.Follower);
		left2.set(RobotMap.TALON_LEFT_1);
		/* set to follow main left talon */
		left3.changeControlMode(TalonControlMode.Follower);
		left3.set(RobotMap.TALON_LEFT_1);
		/* motors are opposite other side */
		right1.setInverted(true);
		/* enable current limit */
		right1.setCurrentLimit(MAX_AMPS);
		right1.EnableCurrentLimit(true);
		/* set to follow main right talon */
		right2.changeControlMode(TalonControlMode.Follower);
		right2.set(RobotMap.TALON_RIGHT_1);
		/* set to follow main right talon*/
		right3.changeControlMode(TalonControlMode.Follower);
		right3.set(RobotMap.TALON_RIGHT_1);

		try {
			/* init navigation board */
			sensorBoard = new AHRS(SPI.Port.kMXP);
			getSensorBoard().reset();
			navigationAvaliable = true;
		} catch (Exception e) {
			System.out.println("Error instantating sensorBoard : " + e.getMessage());
		}
	}
	
	public void shift(boolean highGear) 
	{
		isHighGear = highGear;
		/* shift to opposite gear */
		if (highGear)
			leftSolenoid.set(true);
		else 
			rightSolenoid.set(true);
		/* turn solenoid off after 1/4 of second */
		Scheduler.getInstance().add(new DelayedCallback(0.25)
		{
			public void onCallback() {
				leftSolenoid.set(false);
				rightSolenoid.set(false);
			}
		});
	}
	
	/* standard arcade drive */
	public void arcadeDrive(double move, double rotate) 
	{
		double leftSpeed = move + rotate;
		double rightSpeed = move - rotate;
		/* other talons are followers */
		left1.set(leftSpeed);
		right1.set(rightSpeed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public boolean isHighGear() {
		return isHighGear;
	}

	public boolean isNavigationAvaliable() {
		return navigationAvaliable;
	}

	public AHRS getSensorBoard() {
		return sensorBoard;
	}
	
	public double getLinearAccelY()
	{
		return sensorBoard.getWorldLinearAccelX();
	}
	
	public double getLinearAccelX()
	{
		return sensorBoard.getWorldLinearAccelY();
	}
	
	public double getLeftDistance()
	{
		return driveEncoderLeft.getDistance();
	}
	
	public double getRightDistance()
	{
		return driveEncoderRight.getDistance();
	}
	
	public double getDistance()
	{
		return -(driveEncoderLeft.getDistance() + driveEncoderRight.getDistance()) / 2.0;
	}
	
	public void resetDistance()
	{
		driveEncoderLeft.reset();
		driveEncoderRight.reset();
	}
	
	public double getAngle()
	{
		return sensorBoard.getAngle();
	}
}
