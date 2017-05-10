package org.usfirst.frc.team2907.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2907.robot.commands.AlignTowerCommand;
import org.usfirst.frc.team2907.robot.commands.BackUpGearCommand;
import org.usfirst.frc.team2907.robot.commands.ClimbCommand;
import org.usfirst.frc.team2907.robot.commands.ClimberPowerCommand;
import org.usfirst.frc.team2907.robot.commands.OpenGearIntakeCommand;
import org.usfirst.frc.team2907.robot.commands.ShiftCommand;
import org.usfirst.frc.team2907.robot.commands.ShooterPIDCommand;
import org.usfirst.frc.team2907.robot.commands.SpinUpShooterCommand;
import org.usfirst.frc.team2907.robot.commands.WaitForGearCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	private boolean guitarMode;				// boolean flag to enable guitar hero controller as manipulator
	
	public Joystick driveStick = new Joystick(0);		// drivers ps4 joystick
	public Joystick manipulatorStick = new Joystick(1);	// manipulator ps4 joystick

	/* MANIPULATOR GUITAR FIELDS */
	public JoystickButton fastClimbButton;			// fast climb button on guitar
	public JoystickButton slowClimbButton;			// slow climb button on guitar
	public JoystickButton gearButton;			// open gear mechanism on guitar
	/* MANIPULATOR PS4 FIELDS */
	public JoystickButton spinUpButton;			// spins up shooter
	public JoystickButton openGearButton;			// opens gear mechanism
	/* DRIVER PS4 FIELDS */
	public JoystickButton alignButton = new JoystickButton(driveStick, 4);	// camera align on triangle
	public JoystickButton backUpButton = new JoystickButton(driveStick, 1); // backs up set distance
	public JoystickButton shiftButton = new JoystickButton(driveStick, 6); 	// shift on right bumper
	public JoystickButton maxButton = new JoystickButton(driveStick, 5); 	// no limits on left bumper (not used)

	public OI(boolean guitarMode)
	{
		if (guitarMode)
		{
			/* init guitar fields if used */
			this.guitarMode = guitarMode;
			 fastClimbButton = new JoystickButton(manipulatorStick, 2);
			 slowClimbButton = new JoystickButton(manipulatorStick, 6);
			 gearButton = new JoystickButton(manipulatorStick, 3);
			 fastClimbButton.whileHeld(new ClimberPowerCommand(1));
			 slowClimbButton.whileHeld(new ClimberPowerCommand(.25));
		} else
		{
			/* init ps4 fields */
			spinUpButton = new JoystickButton(manipulatorStick, 2);		// spinup on 'x' button
			openGearButton = new JoystickButton(manipulatorStick, 3);	// open gear mech on 'o' button
			openGearButton.whenPressed(new OpenGearIntakeCommand());	// register open gear mech command 
			// spinUpButton.whileHeld(new ShooterPIDCommand(50));		// used when testing velocity control on shooter
			spinUpButton.whileHeld(new SpinUpShooterCommand());		// register shooter command
		}
		
		alignButton.whileHeld(new AlignTowerCommand(.25));			// register align tower command
		shiftButton.whenPressed(new ShiftCommand());				// register shift drivetrain command
		backUpButton.whenPressed(new BackUpGearCommand());			// register loading lane back up command
	}

	public boolean isGuitarMode()
	{
		return guitarMode;
	}
}
