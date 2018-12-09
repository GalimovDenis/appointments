package appointer.commands;

import appointer.calendar.event.IEvent;

public class CmdAddAttendee extends CmdLeaf implements AppCommand {
	
	private final String user; 
	private final IEvent event;  
	private String currentAttendee;
 		
	public CmdAddAttendee(String LocalName, IEvent event) {
		super();
		this.user = LocalName;
		this.event = event;
	}

	@Override 
	public void execute() {
		currentAttendee = event.getAttendee();
		event.setAttendee(user);
	}

	@Override
	public void undo() {
		event.setAttendee(currentAttendee);
	}


}
