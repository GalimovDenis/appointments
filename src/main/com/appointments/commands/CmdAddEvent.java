package com.appointments.commands;

import com.appointments.calendar.calendars.ICalendars;
import com.appointments.calendar.event.IAppointmentEvent;

public class CmdAddEvent extends CmdSpecialLeaf implements IAppCommand {

	private final ICalendars appCalendar;
	private final IAppointmentEvent event;
	private boolean executed = false;

	public CmdAddEvent(ICalendars appCalendar, IAppointmentEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		appCalendar.putEvent(event);
		executed = true;
	}

	@Override
	public void undo() {
		if (executed) appCalendar.deleteEvent(event);
	}


}
