package com.controlsystem.app.model;

/**
 * Represents a generic appliance in the smart home system.
 * <p>
 * All appliances must implement this interface to provide basic functionality
 * such as turning on/off, reporting status, and providing a name identifier.
 */
public interface Appliance {

    /**
     * Turns on the appliance.
     */
    void turnOn();

    /**
     * Turns off the appliance.
     */
    void turnOff();

    /**
     * Returns the current status of the appliance.
     *
     * @return a string describing the current state or mode of the appliance
     */
    String getStatus();

    /**
     * Returns the name of the appliance.
     *
     * @return a unique name identifying the appliance
     */
    String getName();
}
