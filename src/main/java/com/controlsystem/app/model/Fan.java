package com.controlsystem.app.model;

import lombok.Getter;

/**
 * Represents a fan appliance in the smart home system.
 * <p>
 * The fan supports variable speeds ranging from 0 (off) to a defined maximum speed.
 * Implements the {@link Appliance} interface.
 */
@Getter
public class Fan implements Appliance {

    /**
     * Constant name identifier for this appliance.
     */
    private static final String NAME = "Fan";

    /**
     * Current speed of the fan. A value of 0 indicates the fan is off.
     */
    private int speed = 0;

    /**
     * Maximum allowable fan speed.
     */
    private static final int MAX_SPEED = 2;

    /**
     * Turns on the fan by setting its speed to 1.
     */
    @Override
    public void turnOn() {
        speed = 1;
    }

    /**
     * Turns off the fan by setting its speed to 0.
     */
    @Override
    public void turnOff() {
        speed = 0;
    }

    /**
     * Sets the speed of the fan.
     *
     * @param level the desired fan speed (0 to {@code MAX_SPEED})
     * @throws IllegalArgumentException if the speed is out of range
     */
    public void setSpeed(int level) {
        if (level < 0 || level > MAX_SPEED) {
            throw new IllegalArgumentException("Invalid fan speed: " + level);
        }
        speed = level;
    }

    /**
     * Returns the current status of the fan.
     *
     * @return a string indicating the current speed and whether it is off
     */
    @Override
    public String getStatus() {
        return "Fan speed: " + speed + (speed == 0 ? " (OFF)" : "");
    }

    /**
     * Returns the name of the appliance.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return NAME;
    }
}
