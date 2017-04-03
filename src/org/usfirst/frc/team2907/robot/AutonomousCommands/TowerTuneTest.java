package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TowerTuneTest extends CommandGroup
{
	public TowerTuneTest()
	{
		addSequential(new AlignTowerCommand(.4));
//		addSequential(new SpinUpShooterCommand());
	}
}
