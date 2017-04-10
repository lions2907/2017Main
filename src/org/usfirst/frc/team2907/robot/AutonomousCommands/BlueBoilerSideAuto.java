package org.usfirst.frc.team2907.robot.AutonomousCommands;

import org.usfirst.frc.team2907.robot.commands.AlignPixyCommand;
import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.DriveDistance;
import org.usfirst.frc.team2907.robot.commands.RotateToAngle;
import org.usfirst.frc.team2907.robot.commands.ShooterPIDCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueBoilerSideAuto extends CommandGroup
{
	public BlueBoilerSideAuto(boolean enableLiftCamera, boolean enableShooter)
	{
		addSequential(new DriveDistance(8, .5));
		addSequential(new RotateToAngle(-15));
		addSequential(new DriveDistance(65, .5));
		addSequential(new DriveDistance(20, .25));
		addSequential(new RotateToAngle(27));
		addSequential(new DriveDistance(8, .25));
		
		if (enableLiftCamera)
			addSequential(new AlignPixyCommand());
		
		addSequential(new DriveDistance(15, .25));
		
		if (enableShooter)
		{
			addSequential(new WaitForGearCommand());
			addParallel(new AlignTowerCommand(.4));
			addSequential(new ShooterPIDCommand(50));
		}
	}
}
