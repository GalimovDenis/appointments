package appointer.commands;

import appointer.calendar.allcalendars.ICalendars;
import appointer.calendar.facades.IEvent;
import appointer.user.SingletonAppUser;

public class CmdAddAppointment extends CmdComposite implements AppCommand {
	
	private final ICalendars appCalendar;
	private final IEvent event;
 	private final String orgname;

 	
	public CmdAddAppointment(ICalendars appCalendar, IEvent event, String organizer) {
		super();
		this.appCalendar = appCalendar;
		this.event = event;
		this.orgname = organizer;
		addSubCommands();
		//TODO: async approve in remote;		
		//TODO: command for CalendarStorage.getValueByName(orgname).addEvent(event);
	}

	private void addSubCommands() {
		add(new CmdAddEvent(appCalendar, event));
		add(new CmdAddAttendee(SingletonAppUser.lazyGet().getName(), event));
		add(new CmdSetOrganizer(event, orgname));
	}
}
