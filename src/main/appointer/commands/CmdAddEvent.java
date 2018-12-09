package appointer.commands;

import appointer.calendar.allcalendars.ICalendars;
import appointer.calendar.facades.IEvent;

public class CmdAddEvent extends CmdLeaf implements AppCommand {

	private final ICalendars appCalendar;
	private final IEvent event;
	private boolean executed = false;

	public CmdAddEvent(ICalendars appCalendar, IEvent event) {
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
