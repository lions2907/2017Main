package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap					// final code 2017
{
	public static final int TALON_LEFT_1 = 5;   	// drive cim
	public static final int TALON_LEFT_2 = 9;   	// drive cim
	public static final int TALON_LEFT_3 = 8;   	// drive cim
	public static final int TALON_RIGHT_1 = 3;  	// drive cim
	public static final int TALON_RIGHT_2 = 10; 	// drive cim 
	public static final int TALON_RIGHT_3 = 11; 	// drive cim
	
	public static final int TALON_CLIMB_1 = 44;	// climber 775	
	public static final int TALON_CLIMB_2 = 6;	// climber 775
	
	public static final int TALON_SHOOTER = 55;	// shooter mini cim
	public static final int TALON_INTAKE_SHOOT = 4;	// shooter intake bag
	
	public static final int TALON_INTAKE_1 = 22;	// floor intake bag
	public static final int TALON_INTAKE_2 = 7;	// floor intake bag
	
	public static final int SOLENOID_DRIVE_1 = 2;	
	public static final int SOLENOID_DRIVE_2 = 3;
	public static final int SOLENOID_INTAKE_1 = 0;
	public static final int SOLENOID_INTAKE_2 = 1;
	
	public static final int DRIVE_ENCODER_RCH1 = 1;	// right drive encoder read pin on DIO
	public static final int DRIVE_ENCODER_RCH2 = 2;	// right drive encoder write pin on DIO
	public static final int DRIVE_ENCODER_LCH1 = 6;	// left drive encoder read pin on DIO
	public static final int DRIVE_ENCODER_LCH2 = 7;	// left drive encoder write pin on DIO
	public static final int SHOOTER_ENCODER_1 = 9;	// shooter encoder write pin, moved to SRX expansion
	public static final int SHOOTER_ENCODER_2 = 8;	// shooter encoder read pin, moved to SRX expansion
	
	public static final int ULTRASONIC_G_WRITE = 5;	// gear mechanism ultrasonic write pin on DIO
	public static final int ULTRASONIC_G_READ = 4;	// gear mechanism ultrasnoic read pin on DIO
}
