package appointer.commands;


import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IAppointmentEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent extends CmdSpecialLeaf implements IAppCommand {

	private final ICalendars appCalendar;
	private final IAppointmentEvent event;
	private boolean executed = false;

	public CmdRemoveEvent(ICalendars appCalendar, IAppointmentEvent event) {
		this.appCalendar = appCalendar;
		this.event = event;
	}
	
	@Override
	public void execute() {
		executed = appCalendar.deleteEvent(event);
	}

	@Override
	public void undo() {
		if (executed) appCalendar.putEvent(event);
	}
}
