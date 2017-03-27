package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2907.robot.commands.BackUpGearCommand;
import org.usfirst.frc.team2907.robot.commands.ClearIntakeCommand;
import org.usfirst.frc.team2907.robot.commands.ClimbCommand;
import org.usfirst.frc.team2907.robot.commands.ShiftCommand;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	public Joystick driveStick = new Joystick(0);
	public Joystick manipulatorStick = new Joystick(1);
	
	public JoystickButton spinUpButton = new JoystickButton(manipulatorStick, 2); // spinup on 'x' button
	public JoystickButton backUpButton = new JoystickButton(driveStick, 1);	// backs up set distance
	public JoystickButton shiftButton = new JoystickButton(driveStick, 6); // shift on right bumper
	public JoystickButton maxButton = new JoystickButton(driveStick, 5); // no limits on left bumper aka TURBO POWER
	
	public OI()
	{
		shiftButton.whenPressed(new ShiftCommand());
		spinUpButton.whileHeld(new SpinUpShooterCommand());
		backUpButton.whenPressed(new BackUpGearCommand());
	}
}
