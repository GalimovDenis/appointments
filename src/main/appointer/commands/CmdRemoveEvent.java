package appointer.commands;


import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IBuilderEvent;

/**
 * 
 * Removing event from local user's calendar;
 *
 */
public class CmdRemoveEvent extends CmdSpecialLeaf implements IAppCommand {

	private final ICalendars appCalendar;
	private final IBuilderEvent event;
	private boolean executed = false;

	public CmdRemoveEvent(ICalendars appCalendar, IBuilderEvent event) {
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
