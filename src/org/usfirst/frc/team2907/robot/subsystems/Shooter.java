package org.usfirst.frc.team2907.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.RobotMap;
import org.usfirst.frc.team2907.robot.commands.DelayedCallback;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem
{
	private CANTalon talonShooter = new CANTalon(RobotMap.TALON_SHOOTER);
	private CANTalon talonIntake = new CANTalon(RobotMap.TALON_INTAKE_SHOOT);
	private boolean isShooting = false;
	private boolean isSpeedControlledEnabled = false;
	private double power = 0.85;
	private double spinUpTime = 2;

	public Shooter()
	{
		talonShooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonShooter.setF(0.04833);
		talonShooter.setP(0.001);
		talonShooter.setI(0);
		talonShooter.setD(0);
		talonShooter.configNominalOutputVoltage(0, 0);
		talonShooter.configPeakOutputVoltage(12, -12);
	}

	public void enableSpeedController()
	{
		isSpeedControlledEnabled = true;
		talonShooter.changeControlMode(TalonControlMode.Speed);
	}

	public void disableSpeedController()
	{
		isSpeedControlledEnabled = false;
		talonShooter.changeControlMode(TalonControlMode.PercentVbus);
	}

	@Override
	protected void initDefaultCommand()
	{
	}

	public void spinUp(double delay)
	{
		isShooting = true;
		Scheduler.getInstance().add(new DelayedCallback(delay)
		{
			public void onCallback()
			{
				if (isShooting)
					intake(-1);
			}
		});
	}

	public void shoot(double rpm)
	{
		talonShooter.set(rpm);
	}

	public void stop()
	{
		isShooting = false;
		talonIntake.set(0);
		talonShooter.set(0);
	}

	public void intake(double power)
	{
		talonIntake.set(-power);
	}

	public double getRPM()
	{
		return talonShooter.getEncVelocity();
	}

	public boolean isEnabled()
	{
		return isShooting;
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
