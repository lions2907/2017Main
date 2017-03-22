package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterBlueAuto extends CommandGroup
{
	public CenterBlueAuto()
	{
		addSequential(new DriveDistance(56, .75));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistanceBack(12, -.4));
		addSequential(new RotateToAngle(50));
		addSequential(new AlignTowerCommand(.4, true));
//		if (Robot.cameraManager.aligned)
//			addSequential(new SpinUpShooterCommand());
	}
}
