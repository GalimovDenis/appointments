package appointer.calendar.allcalendars;

import java.util.List;

import appointer.calendar.facades.ControlledEvent;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Uid;


/**
 * Represents user, local calendar and all remote calendars; 
 * Responsible for creating and approving events across the network of app users;
 */
public class Calendars implements ICalendarFacade {
	
	IUser user;
	ICalendar localCalendar; 
	
	public Calendars(String name) {
		user = new AppUser(name);
		CalendarStorage.addCalendar(name);
		localCalendar = CalendarStorage.getCalendar(name);
	}
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendar#AddRemoteUser(java.lang.String)
	 */
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendarFacade#AddRemoteUser(java.lang.String)
	 */
	@Override
	public void AddRemoteUser(String name) {
		if (name == user.getName()) throw new IllegalArgumentException();
		CalendarStorage.getCalendar(name);
	}
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendar#getUser()
	 */
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendarFacade#getUser()
	 */
	@Override
	public IUser getUser() {
		return user;
	}
	
	public ICalendar getLocalCalendar() {
		return localCalendar;
	}
	
	

	
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendar#toString()
	 */
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendarFacade#toString()
	 */
	@Override
	public String toString() {
		return localCalendar.toString() + "\n" + 
			CalendarStorage.toStaticString();
	}
	

	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendar#findEventInLocalCalendar(biweekly.property.Uid)
	 */
	
	/* (non-Javadoc)
	 * @see appointer.calendar.allcalendars.ICalendarFacade#findEventInLocalCalendar(biweekly.property.Uid)
	 */
	@Override
	public ControlledEvent findEventInLocalCalendar(Uid uid) {
		
		List<VEvent> events = getLocalCalendar().getEvents();

		VEvent event = null; 
	
		for (VEvent ve : events) {
			if (ve.getUid().equals(uid)) {
				event = ve;
			}
		}
		return event;
	}

	//flywheel pattern here needed
	
//	public ControlledEvent findEventInLocalCalendar(Uid uid) {
//		
//		VEvent event = localCalendar.findEventInLocalCalendar(Uid uid);
//		pool.getEvent
//		
//	}

	@Override
	public boolean getEvent(Uid uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putEvent(ControlledEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvent(ControlledEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvent(Uid uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ControlledEvent newEvent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
