package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.MoveIntakeCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem
{
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_INTAKE_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_INTAKE_2);
	
	private Ultrasonic ultrasonic = new Ultrasonic(8, 9);
	
	public Intake()
	{
		ultrasonic.setAutomaticMode(true);
	}

	public void move(double speed)
	{
		talon1.set((speed < 1) ? Math.max(speed, -.75) : Math.min(speed, .75));
		talon2.set((speed < 1) ? Math.max(speed, -.75) : Math.min(speed, .75));
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new MoveIntakeCommand());
	}

	public double getDistance()
	{
//		if (!ultrasonic.isRangeValid())
//			return -1;
		return ultrasonic.getRangeInches();
	}
}
