package appointer.commands;

import appointer.calendar.event.IEvent;
import biweekly.property.Organizer;

public class CmdSetOrganizer extends CmdLeaf implements AppCommand {
	
	private final IEvent event;  
	private final String currentOrgName;
	private String previousOrgName;
    
	public CmdSetOrganizer(IEvent event, String organiser) {
		this.event = event;
		this.currentOrgName = organiser;
	}

	@Override ()
	public void execute() {
		previousOrgName = event.getOrganizer();
		event.setOrganizer(currentOrgName);
	}

	@Override
	public void undo() {
		event.setOrganizer(previousOrgName);
	}
}
