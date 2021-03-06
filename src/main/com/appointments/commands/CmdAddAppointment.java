package com.appointments.commands;

import com.appointments.calendar.calendars.ICalendars;
import com.appointments.calendar.event.IAppointmentEvent;

public class CmdAddAppointment extends CmdSpecialComposite implements IAppCommand {
	
	private final ICalendars appCalendar;
	private final IAppointmentEvent event;

 	
	public CmdAddAppointment(ICalendars appCalendar, IAppointmentEvent event) {
		super();
		this.appCalendar = appCalendar;
		this.event = event;
		addSubCommands();
		//TODO: async approve in remote;		
		//TODO: command for CalendarStorage.getValueByName(orgname).addEvent(event);
	}

	private void addSubCommands() {
		add(new CmdAddEvent(appCalendar, event));
	}
}
