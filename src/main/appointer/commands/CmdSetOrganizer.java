package appointer.commands;

import appointer.calendar.event.IBuilderEvent;
import biweekly.property.Organizer;

public class CmdSetOrganizer extends CmdSpecialLeaf implements IAppCommand {
	
	private final IBuilderEvent event;  
	private final String currentOrgName;
	private String previousOrgName;
    
	public CmdSetOrganizer(IBuilderEvent event, String organiser) {
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
