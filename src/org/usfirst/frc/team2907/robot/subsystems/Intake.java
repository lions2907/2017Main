package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.MoveIntakeCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem
{
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_INTAKE_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_INTAKE_2);

	public void move(double speed)
	{
		talon1.set(speed);
		talon2.set(speed);
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new MoveIntakeCommand());
	}

}
