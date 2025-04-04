package com.controlsystem.app.model;

/**
 * Represents a light appliance in the smart home system.
 * <p>
 * This class implements the {@link Appliance} interface and provides basic on/off functionality.
 */
public class Light implements Appliance {

    /**
     * Constant name identifier for this appliance.
     */
    private static final String NAME = "Light";

    /**
     * Indicates whether the light is currently on.
     */
    private boolean isOn = false;

    /**
     * Turns on the light.
     */
    @Override
    public void turnOn() {
        isOn = true;
    }

    /**
     * Turns off the light.
     */
    @Override
    public void turnOff() {

        isOn = false;
    }

    /**
     * Returns the current status of the light.
     *
     * @return "Light is ON" if the light is on, otherwise "Light is OFF"
     */
    @Override
    public String getStatus() {
        return isOn ? "Light is ON" : "Light is OFF";
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
