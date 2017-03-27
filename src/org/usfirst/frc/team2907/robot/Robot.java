package org.usfirst.frc.team2907.robot;

import org.usfirst.frc.team2907.robot.commands.BlueLeftWallAuto;
import org.usfirst.frc.team2907.robot.commands.CenterBlueAuto;
import org.usfirst.frc.team2907.robot.commands.CenterRedAuto;
import org.usfirst.frc.team2907.robot.commands.HighGoalTest;
import org.usfirst.frc.team2907.robot.commands.MTBlueLeftGear;
import org.usfirst.frc.team2907.robot.commands.MTBlueRightGear;
import org.usfirst.frc.team2907.robot.commands.MTRedLeftGear;
import org.usfirst.frc.team2907.robot.commands.MTRedRightGear;
import org.usfirst.frc.team2907.robot.commands.StraightGearAuto;
import org.usfirst.frc.team2907.robot.subsystems.CameraManager;
import org.usfirst.frc.team2907.robot.subsystems.Climber;
import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2907.robot.subsystems.Intake;
import org.usfirst.frc.team2907.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot
{
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final CameraManager cameraManager = new CameraManager();
	public static final Shooter shooter = new Shooter();
	public static final Climber climber = new Climber();
	public static final Intake intake = new Intake();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		oi = new OI();
		chooser.addObject("Red Right Gear Auto", new MTRedRightGear());
		chooser.addObject("Blue Right Gear Auto", new MTBlueRightGear());
		chooser.addObject("Red Left Gear Auto", new MTRedLeftGear());
		chooser.addObject("Blue Left Gear Auto", new MTBlueLeftGear());
		chooser.addObject("Shooting test", new HighGoalTest());
		chooser.addObject("Red Center Gear+Shoot Auto", new CenterRedAuto());
		chooser.addObject("Blue Center Gear+Shoot Auto", new CenterBlueAuto());
		chooser.addObject("Old Blue auto", new BlueLeftWallAuto());
		chooser.addObject("Straight Gear Auto", new StraightGearAuto());
		SmartDashboard.putData("Auto mode", chooser);
		double power = Preferences.getInstance().getDouble("ShooterPower", .85);
		double spinUpTime = Preferences.getInstance()
				.getDouble("SpinUpTime", 2);
		shooter.setSpinUpTime(spinUpTime);
		shooter.setPower(power);

		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{
	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * 
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
