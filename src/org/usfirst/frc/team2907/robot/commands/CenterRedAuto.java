package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterRedAuto extends CommandGroup
{
	public CenterRedAuto()
	{
		addSequential(new DriveDistance(56, .75));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistanceBack(12, -.4));
		addSequential(new RotateToAngle(-30));
		addSequential(new AlignTowerCommand(.4));
		addParallel(new SpinUpShooterCommand());
	}
}
