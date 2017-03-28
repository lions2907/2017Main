package AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueRightWallAuto extends CommandGroup
{
	public BlueRightWallAuto()
	{
		addSequential(new DriveDistance(72, .5));
		addSequential(new RotateToAngle(-55));
		addSequential(new DriveDistance(36, .5));
		addSequential(new AlignPixyCommand());
		addSequential(new DriveDistance(18, .25));
	}
}
