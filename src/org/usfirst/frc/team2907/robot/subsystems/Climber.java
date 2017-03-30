package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.ClimbCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem
{
	// CLIMBING TALONS
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_CLIMB_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_CLIMB_2);
	
	public Climber()
	{
		talon1.changeControlMode(TalonControlMode.Voltage);
		talon2.changeControlMode(TalonControlMode.Follower);
		talon2.set(RobotMap.TALON_CLIMB_1);
	}
	
	public void climb(double speed)
	{
		talon1.set(speed * 12); // in voltage mode add 12 volt scale 
//		talon2.set(speed);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ClimbCommand());
	}

}
