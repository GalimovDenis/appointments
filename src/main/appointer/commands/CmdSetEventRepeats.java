package appointer.commands;

import biweekly.component.VEvent;
import biweekly.property.RecurrenceRule;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

public class CmdSetEventRepeats  extends CmdSpecialLeaf implements IAppCommand {
	
	private final VEvent event; 
 	private final Frequency frequency;
 	private RecurrenceRule currentRule;
 	private RecurrenceRule previousRule;
	
	
	public CmdSetEventRepeats(VEvent event, Frequency frequency) {
		this.event = event;
		this.frequency = frequency;
	}

	@Override 
	public void execute() {
		previousRule = event.getRecurrenceRule();
		Recurrence recur = new Recurrence.Builder(frequency).build();
		currentRule = new RecurrenceRule(recur);
		event.setRecurrenceRule(currentRule);
	}

	@Override
	public void undo() {
		event.setRecurrenceRule(previousRule);
	}
}
