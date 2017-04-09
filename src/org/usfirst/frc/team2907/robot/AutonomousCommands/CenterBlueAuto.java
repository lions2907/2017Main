package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistanceBack;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

public class CenterBlueAuto extends CenterGearAuto
{
	public CenterBlueAuto(boolean enableShooter)
	{
		super();
		addSequential(new DriveDistanceBack(6, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistanceBack(12, -.4));
		addSequential(new RotateToAngle(50));
		addSequential(new AlignTowerCommand(.4));
		if (enableShooter)
			addSequential(new SpinUpShooterCommand());
	}
	
	
}
