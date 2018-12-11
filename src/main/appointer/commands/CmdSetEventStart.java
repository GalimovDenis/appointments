package appointer.commands;

import java.time.LocalDateTime;

import appointer.calendar.event.IAppointmentEvent;
import appointer.calendar.event.IBuilderEvent;

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
		IBuilderEvent builder = IBuilderEvent.create();
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
