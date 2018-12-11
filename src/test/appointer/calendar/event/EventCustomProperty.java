package appointer.calendar.event;

import biweekly.ICalDataType;
import biweekly.component.VEvent;

public class EventCustomProperty {
	
	public static void main(String[] args) {
		IBuilderEvent event = IBuilderEvent.create();
		VEvent vEvent = new VEvent();
		vEvent.addExperimentalProperty("Registered", ICalDataType.BOOLEAN, "false");
		vEvent.addExperimentalProperty("Responded", ICalDataType.BOOLEAN, "false");
		vEvent.addExperimentalProperty("Complete", ICalDataType.BOOLEAN, "false");
		
		System.out.println(vEvent.getExperimentalProperty("Registered").getValue()); // string
	}
}
