package com.controlsystem.app.scheduler;

import com.controlsystem.app.service.ApplianceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled component responsible for performing periodic system updates.
 * <p>
 * This component turns off all devices as part of a maintenance routine using the {@link ApplianceService}.
 */
@Component
public class SystemUpdate {

    private final ApplianceService applianceService;

    /**
     * Constructs a new SystemUpdate with the given ApplianceService.
     *
     * @param applianceService the service used to control appliances
     */
    public SystemUpdate(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    /**
     * Scheduled task that performs a system update by turning off all appliances. The task will run at 1:00 AM on January 1st every year.
     */
    @Scheduled(cron = "0 0 1 1 1 *") // Runs at 1:00 AM on Jan 1st every year
    public void performSystemUpdate() {
        System.out.println("System update in progress...");
        applianceService.turnOffAllDevices();
    }
}
