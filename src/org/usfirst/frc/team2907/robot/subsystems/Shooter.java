package org.usfirst.frc.team2907.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.DelayedCallback;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem
{
	public static double SPINUP_TIME = 2;
	private CANTalon talon1 = new CANTalon(RobotMap.TALON_SHOOTER);
	private CANTalon intakeTalon = new CANTalon(RobotMap.TALON_INTAKE_SHOOT);
//	private Relay relay = new Relay();
	private boolean spinning = false;
//	private Solenoid solenoid = new Solenoid(RobotMap.SOLENOID_SHOOTER_1);
//	private Solenoid solenoid1 = new Solenoid(RobotMap.SOLENOID_SHOOTER_2);
	private Timer timer;
	private boolean status;
	private boolean enabled = false;
	private double power = 0.85;
	private double spinUpTime = 2;

	public Shooter()
	{
	}

	@Override
	protected void initDefaultCommand()
	{
	}

	public void startTimer()
	{
		status = true;
		setEnabled(true);
		if (timer == null)
		{
//			timer = new Timer();
//			timer.scheduleAtFixedRate(shooterTask, 0, 500);
		}
	}

	public void stopTimer()
	{
		setEnabled(false);
		// timer.cancel();
		// timer = null;
	}

//	private TimerTask shooterTask = new TimerTask()
//	{
//		@Override
//		public void run()
//		{
//			if (isEnabled())
//			{
//				System.out.println("Timer : " + status);
//				if (status)
//					intake(-1);
//				else 
//					intake(0);
//				
//				status = !status;
//			}
//		}
//	};

//	public void shift(boolean on)
//	{
//		status = on;
//		if (status)
//			solenoid.set(true);
//		else
//			solenoid1.set(true);
//		// if (highGear) {
//		// leftSolenoid.set(!highGear);
//		// rightSolenoid.set(highGear);
//		// isHighGear = true;
//		// } else {
//		// leftSolenoid.set(!highGear);
//		// rightSolenoid.set(highGear);
//		// isHighGear = false;
//		// }
//		Scheduler.getInstance().add(new DelayedCallback(0.25)
//		{
//			public void onCallback()
//			{
//				solenoid.set(false);
//				solenoid1.set(false);
//				// shifter.set(DoubleSolenoid.Value.kOff);
//			}
//		});
//	}
//	
	public void spinUp(double delay)
	{
		enabled = true;
		Scheduler.getInstance().add(new DelayedCallback(delay)
		{
			public void onCallback()
			{
				if (enabled)
					intake(-1);
			}
		});
	}
	
	public void intake(double power)
	{
		intakeTalon.set(power);
	}

	public void shoot(double power)
	{
		talon1.set(power);
	}

	public void shoot(boolean on)
	{
		spinning = on;
		if (spinning)
			talon1.set(-power);
		else
			talon1.set(0);
	}

	public void rumble(final boolean on, double delay)
	{
		Scheduler.getInstance().add(new DelayedCallback(delay)
		{
			public void onCallback()
			{
				Robot.oi.manipulatorStick.setRumble(RumbleType.kLeftRumble,
						(on) ? 1 : 0);
				Robot.oi.manipulatorStick.setRumble(RumbleType.kRightRumble,
						(on) ? 1 : 0);
			}
		});
	}

	public boolean isSpinning()
	{
		return spinning;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public double getPower()
	{
		return power;
	}

	public void setPower(double power)
	{
		this.power = power;
	}

	public double getSpinUpTime()
	{
		return spinUpTime;
	}

	public void setSpinUpTime(double spinUpTime)
	{
		this.spinUpTime = spinUpTime;
	}

}
