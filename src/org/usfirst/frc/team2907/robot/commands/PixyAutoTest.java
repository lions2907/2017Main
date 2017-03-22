package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PixyAutoTest extends CommandGroup
{
	public PixyAutoTest()
	{
//		addSequential(new ShooterCommand(true));
		addSequential(new AlignTowerCommand(.25, true));
		addSequential(new ShootTimeCommand(10));
//		addSequential(new StartShooterIntakeCommand(true));
//		addSequential(new AlignPixyCommand());
//		addSequential(new DrivePixyCommand(.25));
	}
}
