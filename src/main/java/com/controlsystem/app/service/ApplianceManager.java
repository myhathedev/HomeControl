package com.controlsystem.app.service;

import com.controlsystem.app.model.*;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of appliances in the smart home system.
 * <p>
 * Responsible for initializing appliances, retrieving them by name,
 * and performing operations such as turning all appliances off.
 */
@Getter
@Component
public class ApplianceManager {

    /**
     * The list of all managed appliances.
     */
    private final List<Appliance> appliances = new ArrayList<>();

    /**
     * Constructs the manager and initializes the default set of appliances:
     * {@link Light}, {@link Fan}, and {@link AirConditioner}.
     */
    public ApplianceManager() {
        appliances.add(new Light());
        appliances.add(new Fan());
        appliances.add(new AirConditioner());
    }

    /**
     * Retrieves an appliance by its name (case-insensitive).
     *
     * @param name the name of the appliance to look up
     * @return the matching {@link Appliance} instance, or {@code null} if not found
     */
    public Appliance getApplianceByName(String name) {
        return appliances.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Turns off all appliances managed by this manager.
     */
    public void turnOffAll() {
        // Turn off each appliance
        appliances.forEach(Appliance::turnOff);
        System.out.println("Turn off all devices.");
    }
}
