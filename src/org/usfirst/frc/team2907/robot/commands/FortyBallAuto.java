package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FortyBallAuto extends CommandGroup
{
	public FortyBallAuto()
	{
		addSequential(new DriveDistance(111, .7));
		addSequential(new RotateToAngle(90));
		addSequential(new DriveDistanceCollision(60, .7));
		addSequential(new DriveDistance(12, -.25));
		addSequential(new RotateToAngle(90));
//		addSequential(new AlignTowerCommand(.25));
		addParallel(new SpinUpShooterCommand());
	}
}
