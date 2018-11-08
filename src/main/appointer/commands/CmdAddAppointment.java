package appointer.commands;

import java.util.ArrayList;
import java.util.List;

import appointer.user.SingletonAppUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class CmdAddAppointment implements AppCommand {
	
	private final ICalendar appCalendar;
	private final VEvent event; 
 	private final String orgname;
	private List<AppCommand> appCommands = new ArrayList<>();
 	
	public CmdAddAppointment(ICalendar appCalendar, VEvent event, String organiser) {
		this.appCalendar = appCalendar;
		this.event = event;
		this.orgname = organiser;
		add(new CmdAddEvent(appCalendar, event));
		add(new CmdAddAttendee(SingletonAppUser.lazyGet().getName(), event));
		add(new CmdSetOrganiser(event, orgname));
		//TODO: async approve in remote;		
		//TODO: command for CalendarStorage.getValueByName(orgname).addEvent(event);
	}

	//total need of safe copying here original command must be referenced to keep remove() working;
	public void add(AppCommand appCommand) {
		appCommands.add(appCommand);
	}

	public void remove(AppCommand appCommand) {
		appCommands.remove(appCommand);
	}

	public AppCommand getChild(int i) {
		return appCommands.get(i);
	}

	@Override
	public void execute() {
		for (int i = 0; i < appCommands.size(); i++) {
			appCommands.get(i).execute();
		}
	}

	@Override
	public void undo() {
		for (int i = appCommands.size() - 1; i >= 0; i--) {
			appCommands.get(i).undo();
		}
	}

}
