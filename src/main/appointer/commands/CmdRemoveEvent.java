package appointer.commands;


import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent extends CmdLeaf implements AppCommand {

	private final ICalendars appCalendar;
	private final IEvent event;
	private boolean executed = false;

	public CmdRemoveEvent(ICalendars appCalendar, IEvent event) {
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
