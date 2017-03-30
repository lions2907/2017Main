package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	public static final int TALON_LEFT_1 = 5; // cim
	public static final int TALON_LEFT_2 = 9; // cim
//	public static final int TALON_LEFT_3 = 4; // mini cim
	public static final int TALON_RIGHT_1 = 3; // cim
	public static final int TALON_RIGHT_2 = 10; // cim 
//	public static final int TALON_RIGHT_3 =11; // mini cim
	
	public static final int TALON_CLIMB_1 = 44;
	public static final int TALON_CLIMB_2 = 6;
	
	public static final int TALON_SHOOTER = 8;
	public static final int TALON_INTAKE_SHOOT = 4;
	
	public static final int TALON_INTAKE_1 = 7;
	public static final int TALON_INTAKE_2 = 22;
	
	public static final int TALON_GEAR_INTAKE = 55;
	
	public static final int SOLENOID_DRIVE_1 = 2;
	public static final int SOLENOID_DRIVE_2 = 3;
	public static final int SOLENOID_INTAKE_1 = 0;
	public static final int SOLENOID_INTAKE_2 = 1;
	
	public static final int DRIVE_ENCODER_RCH1 = 0;
	public static final int DRIVE_ENCODER_RCH2 = 1;
	public static final int DRIVE_ENCODER_LCH1 = 2;
	public static final int DRIVE_ENCODER_LCH2 = 3;
	
	public static final int ULTRASONIC_G_WRITE = 9;
	public static final int ULTRASONIC_G_READ = 8;
}
