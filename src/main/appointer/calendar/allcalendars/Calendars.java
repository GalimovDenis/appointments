package appointer.calendar.allcalendars;

import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;


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
	
}
