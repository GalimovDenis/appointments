package com.appointments.commands;

import com.appointments.calendar.event.IEventBuilder;

public class CmdAddAttendee extends CmdSpecialLeaf implements IAppCommand {
	
	private final String user; 
	private final IEventBuilder event;  
	private String currentAttendee;
 		
	public CmdAddAttendee(String LocalName, IEventBuilder event) {
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
