package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.commands.ReadLidar;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lidar extends Subsystem
{
	private static final int LIDAR_ADDRESS = 0x62;
	private static final int DATA_SIZE = 2;
	private I2C wire;
	private byte[] buffer;
	
	public Lidar()
	{
		wire = new I2C(I2C.Port.kMXP, LIDAR_ADDRESS);
	}
	
	public int read()
	{
		buffer = new byte[DATA_SIZE];
		wire.write(0x00, 0x04);
		Timer.delay(0.04);
		wire.read(0x8f, DATA_SIZE, buffer);
		return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ReadLidar());
	}

}
