package dev.dashaun.ui.calendar.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("calendar.service")
public record ConfigProps(String url) {
}
