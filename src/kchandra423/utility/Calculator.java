package kchandra423.utility;

/**
 * A basic utility to do basic calculations. Currently only calculates angles between points
 *
 * @author Kumar Chandra
 */
public class Calculator {
    /**
     * Calcultates the angle from one point to another. All values are given in the format of the math class' atan2 method
     * @param x1 The x coordinate of the starting point
     * @param y1 The y coordinate of the starting point
     * @param x2 The x coordinate of the ending point
     * @param y2 The y coordinate of the ending point
     * @see Math
     * @return An angle in radians between -pi and pi
     */
    public static double calculateAngle(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2-y1,x2-x1);
    }
}
