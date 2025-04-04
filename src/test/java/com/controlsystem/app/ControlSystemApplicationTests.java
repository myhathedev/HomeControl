package com.controlsystem.app.service;

import com.controlsystem.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ApplianceServiceTest {

	private ApplianceManager mockManager;
	private ApplianceService applianceService;

	private Light light;
	private Fan fan;
	private AirConditioner ac;

	@BeforeEach
	void setUp() {
		mockManager = mock(ApplianceManager.class);
		applianceService = new ApplianceService(mockManager);

		light = new Light();
		fan = new Fan();
		ac = new AirConditioner();

		when(mockManager.getApplianceByName("Light")).thenReturn(light);
		when(mockManager.getApplianceByName("Fan")).thenReturn(fan);
		when(mockManager.getApplianceByName("AirConditioner")).thenReturn(ac);
		when(mockManager.getAppliances()).thenReturn(Arrays.asList(light, fan, ac));
	}

	@Test
	void testTurnOn_Light() {
		String result = applianceService.turnOn("Light");
		assertEquals("Light turned ON.", result);
		assertEquals("Light is ON", light.getStatus());
	}

	@Test
	void testTurnOff_Light() {
		light.turnOn(); // Make sure it was on
		String result = applianceService.turnOff("Light");
		assertEquals("Light turned OFF.", result);
		assertEquals("Light is OFF", light.getStatus());
	}

	@Test
	void testSetFanSpeed() {
		String result = applianceService.setFanSpeed(2);
		assertEquals("Fan speed set to 2", result);
		assertEquals("Fan speed: 2", fan.getStatus());
	}

	@Test
	void testSetFanSpeed_Invalid() {
		String result = applianceService.setFanSpeed(10); // Over max
		assertTrue(result.startsWith("Invalid speed"));
	}

	@Test
	void testSetAcMode_Valid() {
		String result = applianceService.setAcMode("cool");
		assertEquals("AC mode set to COOL", result);
		assertEquals("AC mode: COOL", ac.getStatus());
	}

	@Test
	void testSetAcMode_Invalid() {
		String result = applianceService.setAcMode("freeze");
		assertTrue(result.startsWith("Invalid mode"));
	}

	@Test
	void testGetStatuses() {
		fan.setSpeed(2);
		ac.setMode(AirConditioner.Mode.HEAT);
		List<String> statuses = applianceService.getStatuses();

		assertTrue(statuses.contains("Light is OFF"));
		assertTrue(statuses.contains("Fan speed: 2"));
		assertTrue(statuses.contains("AC mode: HEAT"));
	}

	@Test
	void testTurnOffAllDevices() {
		ApplianceManager realManager = new ApplianceManager();
		ApplianceService applianceService = new ApplianceService(realManager);

		// Get the real Light object from the manager
		Light light = (Light) realManager.getApplianceByName("Light");
		Fan fan = (Fan) realManager.getApplianceByName("Fan");
		AirConditioner ac = (AirConditioner) realManager.getApplianceByName("AirConditioner");

		// Turn them on to simulate active devices
		light.turnOn();
		fan.setSpeed(2);
		ac.setMode(AirConditioner.Mode.HEAT);

		applianceService.turnOffAllDevices();
		assertEquals("Light is OFF", light.getStatus());
		assertEquals("Fan speed: 0 (OFF)", fan.getStatus());
		assertEquals("AC mode: OFF", ac.getStatus());
	}

}