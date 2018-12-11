package appointer.commands;

import java.time.LocalDateTime;

import appointer.calendar.event.IBuilderEvent;

public class CmdSetEventStart extends CmdSpecialLeaf implements IAppCommand {
	
	private final IBuilderEvent event;
	private LocalDateTime currentStart;
	private LocalDateTime previousStart; 
	
	public CmdSetEventStart(IBuilderEvent event, LocalDateTime start) {
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
