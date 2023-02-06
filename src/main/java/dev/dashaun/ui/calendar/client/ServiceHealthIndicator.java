package dev.dashaun.ui.calendar.client;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthIndicator implements HealthIndicator {

	private final CalendarClient calendarClient;

	public ServiceHealthIndicator(CalendarClient calendarClient) {
		this.calendarClient = calendarClient;
	}

	@Override
	public Health health() {
		int errorCode = check();
		if (errorCode != 0) {
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		return Health.up().build();
	}

	private int check() {
		if (!calendarClient.serviceHealth().contains("UP")) {
			return 1;
		}
		return 0; // healthy
	}

}
