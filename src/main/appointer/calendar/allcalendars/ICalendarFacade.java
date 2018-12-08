package appointer.calendar.allcalendars;

import appointer.calendar.facades.ControlledEvent;
import appointer.user.IUser;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public interface ICalendarFacade {

	void AddRemoteUser(String name);
		
	IUser getUser();

	String toString();

	ControlledEvent findEventInLocalCalendar(Uid uid);
	
	boolean getEvent(Uid uid);
	
	boolean putEvent(ControlledEvent event);
	
	boolean deleteEvent(ControlledEvent event);
	
	boolean deleteEvent(Uid uid);
	
	ControlledEvent newEvent();

}