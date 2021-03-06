package org.usfirst.frc.team2907.robot;

import org.usfirst.frc.team2907.robot.AutonomousCommands.BlueBoilerSideAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.BlueLoadingZoneAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterBlueAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterGearAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.CenterRedAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTBlueLeftGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTBlueRightGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTRedLeftGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.MTRedRightGear;
import org.usfirst.frc.team2907.robot.AutonomousCommands.NothingAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.PixyTuneTest;
import org.usfirst.frc.team2907.robot.AutonomousCommands.RedBoilerSideAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.RedLoadingZoneAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.RedRightGearAuto;
import org.usfirst.frc.team2907.robot.AutonomousCommands.StraightNoCamera;
import org.usfirst.frc.team2907.robot.AutonomousCommands.TowerTuneTest;
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
	public static final boolean ENABLE_TUNING = true;
	public static final boolean ENABLE_DRIVE_LOGS = false;
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
		chooser.addObject("Blue Loading Zone Auto", new BlueLoadingZoneAuto());
		chooser.addObject("Red Loading Zone Auto", new RedLoadingZoneAuto());
		
		chooser.addObject("Red Center Gear & Shoot Auto", new CenterRedAuto(false, true));
		chooser.addObject("Blue Center Gear & Shoot Auto", new CenterBlueAuto(false, true));
		
		chooser.addObject("Red Boiler Side Auto", new RedBoilerSideAuto(false, true));
		chooser.addObject("Blue Boiler Side Auto", new BlueBoilerSideAuto(false, true));
		
//		chooser.addObject("Center no camera", new StraightNoCamera());
		
//		chooser.addObject("Nothing Auto(WORST CASE SCENARIO)", new NothingAuto());
		
		if (ENABLE_TUNING)
		{
			// TEMP FOR TESTING AND TUNING SENSORS
			chooser.addObject("PIXY TUNING TEST PROGRAM", new PixyTuneTest());
			chooser.addObject("TOWER TUNING TEST PROGRAM", new TowerTuneTest());
		}
		
		SmartDashboard.putData("Auto mode", chooser);

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
