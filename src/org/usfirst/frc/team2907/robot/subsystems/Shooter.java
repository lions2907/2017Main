package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem
{
	public static double SPINUP_TIME = 2;
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_SHOOTER);
	private boolean spinning = false;
	
	@Override
	protected void initDefaultCommand()
	{
		
	}
	
	public void shoot(double power)
	{
		talon1.set(power);
	}
	
	public void shoot(boolean on)
	{
		spinning = on;
		if (spinning)
			talon1.set(1);
		else 
			talon1.set(0);
	}
	
	public boolean isSpinning()
	{
		return spinning;
	}

}
