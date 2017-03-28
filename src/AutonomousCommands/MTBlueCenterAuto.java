package AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MTBlueCenterAuto extends CommandGroup
{
	public MTBlueCenterAuto()
	{
		addSequential(new DriveDistance(56, .75));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(16, .25));
		addSequential(new WaitForGearCommand());
		addSequential(new DriveDistance(16, -.4));
		addSequential(new RotateToAngle(-90));
//		addSequential(new AlignTowerCommand(.4));
	}
}
