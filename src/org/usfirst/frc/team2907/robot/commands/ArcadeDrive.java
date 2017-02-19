package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveTrain.arcadeDrive(Robot.oi.driveStick.getAxis(AxisType.kY), -Robot.oi.driveStick.getRawAxis(4));
    	//System.out.println("Encoder distance : " + Robot.driveTrain.getDistance());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveTrain.arcadeDrive(0,0);
    }
    
    protected void interrupted() {
    	end();
    }
}
