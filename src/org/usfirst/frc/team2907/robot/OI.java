package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2907.robot.AutonomousCommands.ClimberPowerCommand;
import org.usfirst.frc.team2907.robot.commands.BackUpGearCommand;
import org.usfirst.frc.team2907.robot.commands.ClearIntakeCommand;
import org.usfirst.frc.team2907.robot.commands.ClimbCommand;
import org.usfirst.frc.team2907.robot.commands.OpenGearIntakeCommand;
import org.usfirst.frc.team2907.robot.commands.ShiftCommand;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	private boolean guitarMode;
	public Joystick driveStick = new Joystick(0);
	public Joystick manipulatorStick = new Joystick(1);

	 public JoystickButton fastClimbButton;
	 public JoystickButton slowClimbButton;

	public JoystickButton spinUpButton;
	public JoystickButton openGearButton;

	public JoystickButton backUpButton = new JoystickButton(driveStick, 1); // backs up set distance
	public JoystickButton shiftButton = new JoystickButton(driveStick, 6); // shift on right bumper
	public JoystickButton maxButton = new JoystickButton(driveStick, 5); // no limits on left bumper aka TURBO POWER

	public OI(boolean guitarMode)
	{
		if (guitarMode)
		{
			this.guitarMode = guitarMode;
			 fastClimbButton = new JoystickButton(manipulatorStick, 2);
			 slowClimbButton = new JoystickButton(manipulatorStick, 6);
			 fastClimbButton.whileHeld(new ClimberPowerCommand(1));
			 slowClimbButton.whileHeld(new ClimberPowerCommand(.25));
		} else
		{
			spinUpButton = new JoystickButton(manipulatorStick, 2); // spinup on 'x' button
			openGearButton = new JoystickButton(manipulatorStick, 3); // open gear mech on 'o' button
			shiftButton.whenPressed(new ShiftCommand());
			openGearButton.whenPressed(new OpenGearIntakeCommand());
		}
		spinUpButton.whileHeld(new SpinUpShooterCommand());
		backUpButton.whenPressed(new BackUpGearCommand());
	}

	public boolean isGuitarMode()
	{
		return guitarMode;
	}
}
