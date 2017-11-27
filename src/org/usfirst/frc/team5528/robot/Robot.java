
package org.usfirst.frc.team5528.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5528.robot.commands.Shoot;
import org.usfirst.frc.team5528.robot.subsystems.BasePilotable;
import org.usfirst.frc.team5528.robot.subsystems.Tourelle;
import org.usfirst.frc.team5528.robot.subsystems.Camera;
import org.usfirst.frc.team5528.robot.subsystems.Shooter;
import org.usfirst.frc.team5528.robot.subsystems.ShooterPiston;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	public static final BasePilotable basePilotable = new BasePilotable();
	public static final Shooter shooter = new Shooter();
	public static final Camera camera = new Camera();
	public static final Tourelle tourelle = new Tourelle();
	public static final ShooterPiston shooterPiston = new ShooterPiston();

	
	public static OI oi;
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();	
		camera.startLoop();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		update();
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("pot", Robot.tourelle.getPosition());
		SmartDashboard.putNumber("angle", Robot.basePilotable.getAngle());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	
	
	public void update() {
		
		Shoot.TIMEOUT = Preferences.getInstance().getDouble("shoot_timeout", Shoot.TIMEOUT);
		BasePilotable.P_Angle = Preferences.getInstance().getDouble("p_angle", BasePilotable.P_Angle);
		BasePilotable.I_Angle = Preferences.getInstance().getDouble("i_angle", BasePilotable.I_Angle);
		BasePilotable.D_Angle = Preferences.getInstance().getDouble("d_angle", BasePilotable.D_Angle);
		BasePilotable.TOLERANCE_ANGLE = Preferences.getInstance().getDouble("tolerance_angle", BasePilotable.TOLERANCE_ANGLE);
		Robot.basePilotable.getPidAngle().setPID(BasePilotable.P_Angle, BasePilotable.I_Angle, BasePilotable.D_Angle);
		Robot.basePilotable.getPidAngle().setAbsoluteTolerance(BasePilotable.TOLERANCE_ANGLE);
	
	}
	
	
	
}
