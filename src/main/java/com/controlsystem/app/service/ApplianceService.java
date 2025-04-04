package com.controlsystem.app.service;
import com.controlsystem.app.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that provides operations to control appliances in the system.
 * <p>
 * This class interacts with the {@link ApplianceManager} to perform actions such as turning appliances on or off,
 * retrieving statuses, and setting specific configurations for certain appliances like the Fan or Air Conditioner.
 */
@Service
public class ApplianceService {

    private final ApplianceManager manager;

    /**
     * Constructs a new ApplianceService with the given manager.
     *
     * @param manager the appliance manager responsible for maintaining appliances
     */
    public ApplianceService(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Turns on the appliance with the specified name.
     *
     * @param name the name of the appliance
     * @return a message indicating the result
     */
    public String turnOn(String name) {
        Appliance appliance = manager.getApplianceByName(name);
        if (appliance != null) {
            appliance.turnOn();
            return name + " turned ON.";
        }
        return "Appliance not found.";
    }

    /**
     * Turns off the appliance with the specified name.
     *
     * @param name the name of the appliance
     * @return a message indicating the result
     */
    public String turnOff(String name) {
        Appliance appliance = manager.getApplianceByName(name);
        if (appliance != null) {
            appliance.turnOff();
            return name + " turned OFF.";
        }
        return "Appliance not found.";
    }

    /**
     * Retrieves the current statuses of all appliances.
     *
     * @return a list of status strings
     */
    public List<String> getStatuses() {
        return manager.getAppliances().stream()
                .map(Appliance::getStatus)
                .collect(Collectors.toList());
    }

    /**
     * Turns off all appliances in the system.
     */
    public void turnOffAllDevices() {
        manager.turnOffAll();
    }

    /**
     * Sets the speed of the fan.
     *
     * @param speed the desired fan speed
     * @return a message indicating the result
     */
    public String setFanSpeed(int speed) {
        Appliance appliance = manager.getApplianceByName("Fan");
        if (appliance instanceof Fan) {
            try {
                ((Fan) appliance).setSpeed(speed);
                return "Fan speed set to " + speed;
            } catch (IllegalArgumentException e) {
                return "Invalid speed: " + speed;
            }
        }
        return "Fan not found.";
    }

    /**
     * Sets the mode of the air conditioner.
     *
     * @param modeName the desired mode ("COOL", "HEAT", "OFF")
     * @return a message indicating the result
     */
    public String setAcMode(String modeName) {
        Appliance appliance = manager.getApplianceByName("AirConditioner");
        if (appliance instanceof AirConditioner) {
            try {
                AirConditioner.Mode mode = AirConditioner.Mode.valueOf(modeName.toUpperCase());
                ((AirConditioner) appliance).setMode(mode);
                return "AC mode set to " + mode;
            } catch (IllegalArgumentException e) {
                return "Invalid mode: " + modeName + ". Valid modes: COOL, HEAT, OFF";
            }
        }
        return "AirConditioner not found.";
    }
}
