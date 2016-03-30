package org.usfirst.frc.team3506.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int SHOOTER_PIVOT_TALON_PORT = 0;
    public static final int[] SHOOTER_PIVOT_ENCODER_PORTS = {0, 1};
    public static final int SERVO_PORT = 1;
    public static final int SHOOTER_LIMIT_SWITCH_PORT = 0;
    public static final double ENCODER_TICKS_TO_DEGREE_MODIFIER = 0.0; //UPDATE
}
