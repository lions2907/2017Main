package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.DriveDistanceBack;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

public class CenterBlueAuto extends CenterGearAuto
{
	/* Created at district champs, used at worlds */
	public CenterBlueAuto(boolean enableLiftCamera, boolean enableShooter)
	{
		super(enableLiftCamera);
		addSequential(new DriveDistance(6, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistanceBack(12, -.4));
		addSequential(new RotateToAngle(50));
		addParallel(new AlignTowerCommand(.4));
		if (enableShooter)
			addSequential(new SpinUpShooterCommand());
		addSequential(new AlignTowerCommand(.4));
		if (enableShooter)
			addSequential(new SpinUpShooterCommand());
	}
	
	
}
