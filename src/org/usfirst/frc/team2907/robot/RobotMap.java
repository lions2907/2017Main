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
	public static final int TALON_LEFT_1 = 3;
	public static final int TALON_LEFT_2 = 8;
	public static final int TALON_LEFT_3 = 11;
	public static final int TALON_RIGHT_1 = 5;
	public static final int TALON_RIGHT_2 = 6;
	public static final int TALON_RIGHT_3 = 44;
	
	public static final int SOLINOID_1 = 0;
	public static final int SOLINOID_2 = 1;
	
	public static final int DRIVE_ENCODER_RCH1 = 0;
	public static final int DRIVE_ENCODER_RCH2 = 1;
	public static final int DRIVE_ENCODER_LCH1 = 2;
	public static final int DRIVE_ENCODER_LCH2 = 3;
}
