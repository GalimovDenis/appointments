package com.appointments.commands;

import com.appointments.calendar.event.IEventBuilder;

import biweekly.property.Organizer;

public class CmdSetOrganizer extends CmdSpecialLeaf implements IAppCommand {
	
	private final IEventBuilder event;  
	private final String currentOrgName;
	private String previousOrgName;
    
	public CmdSetOrganizer(IEventBuilder event, String organiser) {
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
