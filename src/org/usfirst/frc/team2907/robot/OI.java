package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2907.robot.commands.BackUpGearCommand;
import org.usfirst.frc.team2907.robot.commands.ClearIntakeCommand;
import org.usfirst.frc.team2907.robot.commands.ClimbCommand;
import org.usfirst.frc.team2907.robot.commands.ShiftCommand;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driveStick = new Joystick(0);
	public Joystick manipulatorStick = new Joystick(1);
	
	public JoystickButton spinUpButton = new JoystickButton(manipulatorStick, 2); // spinup on button
	public JoystickButton clearIntakeButton = new JoystickButton(manipulatorStick, 3);
	public JoystickButton backUpButton = new JoystickButton(driveStick, 1);
//	public JoystickButton climbButton = new JoystickButton(manipulatorStick, 3);//14); // climb on touchpad
	public JoystickButton shiftButton = new JoystickButton(driveStick, 6); // shift on right bumper
	public JoystickButton maxButton = new JoystickButton(driveStick, 5); // no limits on left bumper
	public OI()
	{
		shiftButton.whenPressed(new ShiftCommand());
		spinUpButton.whileHeld(new SpinUpShooterCommand());
		backUpButton.whenPressed(new BackUpGearCommand());
		//clearIntakeButton.whileHeld(new ClearIntakeCommand());
		//climbButton.whileHeld(new ClimbCommand());
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
