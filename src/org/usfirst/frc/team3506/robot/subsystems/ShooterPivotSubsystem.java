package org.usfirst.frc.team3506.robot.subsystems;

import org.usfirst.frc.team3506.robot.RobotMap;
import org.usfirst.frc.team3506.robot.commands.KeepShooterStaticCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterPivotSubsystem extends PIDSubsystem {
	
	private TalonSRX tilt;
	private Encoder enc;
	private DigitalInput limit;
	private static final double P = 0.1;
	private static final double I = 0.1;
	private static final double D = 0.1;
	
    // Initialize your subsystem here
    public ShooterPivotSubsystem() {
    	super(P, I, D);
    	tilt = new TalonSRX(RobotMap.SHOOTER_PIVOT_TALON_PORT);
    	enc = new Encoder(RobotMap.SHOOTER_PIVOT_ENCODER_PORTS[0], RobotMap.SHOOTER_PIVOT_ENCODER_PORTS[1]);
    	limit = new DigitalInput(RobotMap.SHOOTER_LIMIT_SWITCH_PORT);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new KeepShooterStaticCommand());
    }
    
    public void manualSetMotor(double output){
    	tilt.set(output);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return convertEncoderTicksToDegrees(enc.getDistance());
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	tilt.set(output);
    }
    
    public boolean getLimitState(){
    	return limit.get();
    }
    
    public double convertEncoderTicksToDegrees(double ticks){
    	return RobotMap.ENCODER_TICKS_TO_DEGREE_MODIFIER * ticks;
    }
    
    public void resetEncoder(){
    	enc.reset();
    }
    
    public double getEncoderDistance(){
    	return enc.getDistance();
    }
    
    public void publishEncoderValue(){
    	SmartDashboard.putNumber("Raw encoder distance: ", enc.getDistance());
    	SmartDashboard.putNumber("Raw encoder rate: ", enc.getRate());
    	SmartDashboard.putNumber("Degree encoder distance: ", convertEncoderTicksToDegrees(enc.getDistance()));
    	SmartDashboard.putNumber("Degree encoder rate: ", convertEncoderTicksToDegrees(enc.getRate()));
    }
    
    public void publishToLW(){
    	LiveWindow.addActuator("ShooterPivotSubsystem", "Shooter Pivot Talon", tilt);
    	LiveWindow.addSensor("ShooterPivotSubsystem", "Shooter Pivot Encoder", enc);
    	LiveWindow.addSensor("ShooterPivotSubsystem", "Shooter Limit Switch", limit);
    }
}
