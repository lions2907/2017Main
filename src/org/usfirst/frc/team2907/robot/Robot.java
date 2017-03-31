package org.usfirst.frc.team2907.robot;

import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterBlueAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterGearAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterRedAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTBlueLeftGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTBlueRightGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTRedLeftGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTRedRightGear;
import org.usfirst.frc.team2907.robot.subsystems.CameraManager;
import org.usfirst.frc.team2907.robot.subsystems.Climber;
import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2907.robot.subsystems.IntakeManger;
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
	public static final boolean ENABLE_GUITAR = false;
	/* SUBSYSTEMS WHICH ARE A VIRTUAL MAP OF THE ROBOT */
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final CameraManager cameraManager = new CameraManager();
	public static final Shooter shooter = new Shooter();
	public static final Climber climber = new Climber();
	public static final IntakeManger intake = new IntakeManger();
	/* OI OPERATOR INPUT CONTROLS JOYSTICKS */
	public static OI oi;
	/* AUTONOMOUS MODE SELECTORS */
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit()
	{
		/* INIT JOYSTICKS */
		oi = new OI(ENABLE_GUITAR);
		/* ADD AUTO MODES TO DASHBOARD FOR EASY SELECTING */
		chooser.addObject("Red Right Gear Auto", new MTRedRightGear());
		chooser.addObject("Red Right Gear & Shoot Auto", new MTRedRightGear(true, false));
		
		chooser.addObject("Red Left Gear Auto", new MTRedLeftGear());
		
		chooser.addObject("Blue Left Gear Auto", new MTBlueLeftGear());
		chooser.addObject("Blue Left Gear & Shoot Auto", new MTBlueLeftGear(true, false));
		
		chooser.addObject("Blue Right Gear Auto", new MTBlueRightGear());
		
		chooser.addObject("Center Gear Auto", new CenterGearAuto());
		chooser.addObject("Red Center Gear & Shoot Auto", new CenterRedAuto(false));
		chooser.addObject("Blue Center Gear & Shoot Auto", new CenterBlueAuto(false));
		
//		chooser.addObject("Shooting test", new HighGoalTest());
		SmartDashboard.putData("Auto mode", chooser);
		/* DYNAMICALLY CHANGE SHOOTER POWER VIA DASHBOARD VARIABLES */
		double power = Preferences.getInstance().getDouble("ShooterPower", .85);
		double spinUpTime = Preferences.getInstance()
				.getDouble("SpinUpTime", 2);
		shooter.setSpinUpTime(spinUpTime);
		shooter.setPower(power);
		/* OPENS WEBCAM */
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void disabledInit()
	{
	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		autonomousCommand = chooser.getSelected();

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
