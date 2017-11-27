package org.usfirst.frc.team5528.robot.commands;


import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tourner extends Command {
	private double angle;
	
    public Tourner(double angle) {
       this.angle = angle;
    	
    	// Use requires() here to declare subsystem dependencies
       requires(Robot.basePilotable);
  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.basePilotable.resetGyro();
    	Robot.basePilotable.setSetpointAngle(angle);
    	Robot.basePilotable.enableAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.basePilotable.drivePid();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.basePilotable.angleOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.basePilotable.disableAngle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
