package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

	private double accelX;
	private double accelY;
	
    public ArcadeDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveTrain.arcadeDrive(Robot.oi.driveStick.getAxis(AxisType.kY), -Robot.oi.driveStick.getRawAxis(4));
		accelX = Math.abs(Robot.driveTrain.getSensorBoard().getWorldLinearAccelX());
		accelY = Math.abs(Robot.driveTrain.getSensorBoard().getWorldLinearAccelY());
    	//System.out.println("Encoder distance : " + Robot.driveTrain.getDistance());\
		Robot.oi.driveStick.setRumble(RumbleType.kLeftRumble, (accelY > .6) ? 1 : 0);
		Robot.oi.driveStick.setRumble(RumbleType.kRightRumble, (accelX > .6) ? 1 : 0);
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
