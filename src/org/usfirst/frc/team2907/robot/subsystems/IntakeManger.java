
package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.DelayedCallback;
import org.usfirst.frc.team2907.robot.commands.MoveIntakeCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeManger extends Subsystem
{
	/* speed limit on intake, to fast doesn't gather fuel */
	public static final double POWER_MAX_INTAKE = .75;
	// TALONS CONTROLLING FRONT FUEL INTAKE 
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_INTAKE_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_INTAKE_2);
	/* solenoids controlling gear mechanism */
	private Solenoid leftSolenoid = new Solenoid(RobotMap.SOLENOID_INTAKE_1);
	private Solenoid rightSolenoid = new Solenoid(RobotMap.SOLENOID_INTAKE_2);
	private boolean isOut;
	/* limit switch on NavX board port expansion */
	private DigitalInput limitSwitch = new DigitalInput(10);
	/* ultrasonic not used anymore, replaced by limit switch */
	private Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_G_READ, RobotMap.ULTRASONIC_G_WRITE);
	
	public IntakeManger()
	{
		ultrasonic.setAutomaticMode(true);
	}
	
	public boolean isSwitchFlipped()
	{
		return limitSwitch.get();
	}
	
	public void open(boolean out)
	{
		isOut = out;
		if (isOut)
			leftSolenoid.set(true);
		else 
			rightSolenoid.set(true);

		Scheduler.getInstance().add(new DelayedCallback(0.25) 
		{
			public void onCallback() {
				leftSolenoid.set(false);
				rightSolenoid.set(false);
			}
		});
	}

	public void move(double speed)
	{
		/* add speed limit to intake */
		talon1.set((speed < 1) ? Math.max(speed, -POWER_MAX_INTAKE) : Math.min(speed, POWER_MAX_INTAKE));
		talon2.set((speed < 1) ? Math.max(speed, -POWER_MAX_INTAKE) : Math.min(speed, POWER_MAX_INTAKE));
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new MoveIntakeCommand());
	}

	public double getDistance()
	{
		return ultrasonic.getRangeInches();
	}
	
	public boolean isOut()
	{
		return isOut;
	}
}
