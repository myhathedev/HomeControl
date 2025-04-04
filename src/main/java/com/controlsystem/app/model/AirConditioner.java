package com.controlsystem.app.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an air conditioner appliance in the smart home system.
 * <p>
 * This class implements the {@link Appliance} interface and provides logic to turn on, turn off,
 * and report the current mode of the air conditioner.
 */
@Setter
@Getter
public class AirConditioner implements Appliance {

    /**
     * Constant name identifier for this appliance.
     */
    private static final String NAME = "AirConditioner";

    /**
     * Enum representing the operating modes of the air conditioner.
     */
    public enum Mode {
        OFF, COOL, HEAT
    }

    /**
     * Current operating mode of the air conditioner.
     */
    private Mode mode = Mode.OFF;

    /**
     * Turns on the air conditioner by setting its mode to {@code COOL}.
     */
    @Override
    public void turnOn() {
        mode = Mode.COOL; // Default ON mode
    }

    /**
     * Turns off the air conditioner by setting its mode to {@code OFF}.
     */
    @Override
    public void turnOff() {
        mode = Mode.OFF;
    }

    /**
     * Returns the current status of the air conditioner.
     *
     * @return a string indicating the current mode of the air conditioner
     */
    @Override
    public String getStatus() {
        return "AC mode: " + mode;
    }

    /**
     * Returns the name of this appliance.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return NAME;
    }
}
