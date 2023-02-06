package dev.dashaun.ui.calendar.views;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Event {

	private UUID id = UUID.randomUUID();

	private String name;

	private Date startDate;

	private Date endDate;

	private String url;

	private String content;

}