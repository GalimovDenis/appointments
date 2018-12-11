package appointer.commands;

import appointer.calendar.event.IBuilderEvent;

public class CmdAddAttendee extends CmdSpecialLeaf implements IAppCommand {
	
	private final String user; 
	private final IBuilderEvent event;  
	private String currentAttendee;
 		
	public CmdAddAttendee(String LocalName, IBuilderEvent event) {
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
