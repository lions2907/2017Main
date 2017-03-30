package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.DelayedCallback;
import org.usfirst.frc.team2907.robot.commands.MoveIntakeCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeManger extends Subsystem
{
	public static final double POWER_MAX_INTAKE = .75; // TO FAST ON INTAKE DOESN"T GATHER FUEL
	// TALONS CONTROLLING FRONT FUEL INTAKE 
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_INTAKE_1);
	private CANTalon talon2 = new CANTalon(RobotMap.TALON_INTAKE_2);
	// TALON CONTROLLING GEAR MECHANISM
//	private CANTalon gearTalon = new CANTalon(RobotMap.TALON_GEAR_INTAKE);
	// ULTRASONIC FOR DIGITAL READING OF GEAR (TEMP?)
	private Ultrasonic ultrasonic = new Ultrasonic(8, 9);
	private Solenoid leftSolenoid = new Solenoid(RobotMap.SOLENOID_INTAKE_1);
	private Solenoid rightSolenoid = new Solenoid(RobotMap.SOLENOID_INTAKE_2);
	private boolean isOut;
	
	public IntakeManger()
	{
		ultrasonic.setAutomaticMode(true);
	}
	
	public void open(boolean out)
	{
		isOut = out;
		if (isOut)
			leftSolenoid.set(true);
		else 
			rightSolenoid.set(true);
//		if (highGear) {
//			leftSolenoid.set(!highGear);
//			rightSolenoid.set(highGear);
//			isHighGear = true;
//		} else {
//			leftSolenoid.set(!highGear);
//			rightSolenoid.set(highGear);
//			isHighGear = false;
//		}
		Scheduler.getInstance().add(new DelayedCallback(0.25) {
			public void onCallback() {
				leftSolenoid.set(false);
				rightSolenoid.set(false);
//				shifter.set(DoubleSolenoid.Value.kOff);
			}
		});
	}
	
//	public void moveGear(double speed)
//	{
//		gearTalon.set(speed);
//	}

	public void move(double speed)
	{
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
