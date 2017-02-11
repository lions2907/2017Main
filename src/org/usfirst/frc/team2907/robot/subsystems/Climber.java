package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem
{
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_CLIMB_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_CLIMB_2);
	
	public void climb(double speed)
	{
		talon1.set(speed);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}

}