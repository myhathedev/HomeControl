package com.controlsystem.app.controller;

import com.controlsystem.app.service.ApplianceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * A console-based user interface for interacting with the Smart Home Control System.
 * <p>
 * This component allows users to control appliances such as Light, Fan, and Air Conditioner
 * via a simple command-line menu. It runs in a separate thread upon application startup.
 */
@Component
public class ConsoleInterface {

    private final ApplianceService applianceService;

    /**
     * Constructs a new ConsoleInterface with the given {@link ApplianceService}.
     *
     * @param applianceService the service responsible for managing appliance actions
     */
    @Autowired
    public ConsoleInterface(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    /**
     * Starts the console interface in a new thread after the component has been initialized.
     */
    @PostConstruct
    public void startConsoleInNewThread() {
        new Thread(this::runInterface).start();
    }

    /**
     * Runs the console interface loop for user interaction.
     * Provides a menu to control various appliances and view their statuses.
     */
    private void runInterface() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Simulate startup delay
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Startup interrupted.");
        }

        System.out.println("Welcome to the Smart Home Control System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Light   2. Fan   3. Air Conditioner   4. View status   5. Turn off all devices   0. Exit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLight(scanner);
                    break;
                case "2":
                    handleFan(scanner);
                    break;
                case "3":
                    handleAC(scanner);
                    break;
                case "4":
                    applianceService.getStatuses().forEach(System.out::println);
                    break;
                case "5":
                    applianceService.turnOffAllDevices();
                    break;
                case "0":
                    System.out.println("Shutting down system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Handles user input for controlling the light appliance.
     *
     * @param scanner the Scanner object used to read user input
     */
    private void handleLight(Scanner scanner) {
        System.out.print("Turn light ON or OFF? (on/off): ");
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("on")) {
            System.out.println(applianceService.turnOn("Light"));
        } else if (input.equals("off")) {
            System.out.println(applianceService.turnOff("Light"));
        } else {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Handles user input for setting the fan speed.
     *
     * @param scanner the Scanner object used to read user input
     */
    private void handleFan(Scanner scanner) {
        System.out.print("Set fan speed (0 = OFF, 1-2): ");
        try {
            int speed = Integer.parseInt(scanner.nextLine());
            System.out.println(applianceService.setFanSpeed(speed));
        } catch (NumberFormatException e) {
            System.out.println("Invalid speed.");
        }
    }

    /**
     * Handles user input for setting the air conditioner mode.
     *
     * @param scanner the Scanner object used to read user input
     */
    private void handleAC(Scanner scanner) {
        System.out.print("Set AC mode (cool/heat/off): ");
        String mode = scanner.nextLine().toLowerCase();
        System.out.println(applianceService.setAcMode(mode));
    }
}
