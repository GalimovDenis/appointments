package appointer.commands;

import java.time.LocalDateTime;

import appointer.calendar.event.IEvent;

public class CmdSetEventStart extends CmdLeaf implements AppCommand {
	
	private final IEvent event;
	private LocalDateTime currentStart;
	private LocalDateTime previousStart; 
	
	public CmdSetEventStart(IEvent event, LocalDateTime start) {
		this.event = event;
		this.currentStart = start;
	}

	@Override 	
	public void execute() {
		previousStart = event.getDateTimeStart();
		event.setTimeStart(currentStart);		
	}

	@Override
	public void undo() {
		event.setTimeStart(previousStart);
	}
}
