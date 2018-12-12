package com.appointments.commands;

import java.time.LocalDateTime;

import com.appointments.calendar.event.IAppointmentEvent;
import com.appointments.calendar.event.IEventBuilder;

public class CmdSetEventStart extends CmdSpecialLeaf implements IAppCommand {
	
	private final IAppointmentEvent oldEvent;
	private IAppointmentEvent event;
	private LocalDateTime currentStart;
	private LocalDateTime previousStart; 
	
	public CmdSetEventStart(IAppointmentEvent event, LocalDateTime start) {
		this.oldEvent = event;
		this.event = event;
		this.currentStart = start;
	}

	@Override 	
	public void execute() {
		previousStart = event.getDateTimeStart();
		IEventBuilder builder = IEventBuilder.create();
		builder.setAttendee(event.getAttendee());
		builder.setOrganizer(event.getOrganizer());
		builder.setTimeStart(currentStart);
		builder.setTimeEnd(currentStart); // TODO: preserve time range 
		builder.setEventTimestamp(event.getDateTimeStamp());
		builder.setEventID(event.getUid());
		event = builder.buildAppointment();		
	}

	@Override
	public void undo() {
		event = oldEvent;
	}
}
