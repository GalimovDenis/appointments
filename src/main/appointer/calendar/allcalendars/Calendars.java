package appointer.calendar.allcalendars;

import java.util.List;

import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Uid;


/**
 * Represents user, local calendar and all remote calendars; 
 * Responsible for creating and approving events across the network of app users;
 */
public class Calendars {
	
	IUser user;
	ICalendar localCalendar; 
	
	public Calendars(String name) {
		user = new AppUser(name);
		CalendarStorage.addCalendar(name);
		localCalendar = CalendarStorage.getCalendar(name);
	}
	
	public void AddRemoteUser(String name) {
		if (name == user.getName()) throw new IllegalArgumentException();
		CalendarStorage.getCalendar(name);
	}
	
	public IUser getUser() {
		return user;
	}
	
	public ICalendar getLocalCalendar() {
		return localCalendar;
	}

	public String toString() {
		return localCalendar.toString() + "\n" + 
			CalendarStorage.toStaticString();
	}
	

	public VEvent findEventInLocalCalendar(Uid uid) {
		
		List<VEvent> events = getLocalCalendar().getEvents();

		VEvent event = null; 
		
		for (VEvent ve : events) {
			if (ve.getUid().equals(uid)) {
				event = ve;
			}
		}
		return event;
	}
	
}
