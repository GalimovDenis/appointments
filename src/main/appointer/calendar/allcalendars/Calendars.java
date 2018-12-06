package appointer.calendar.allcalendars;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Uid;

/**
 * Represents user, local calendar and all remote calendars; Responsible for
 * creating and approving events across the network of app users; Remote
 * calendars are needed for free time viewing function
 */
final public class Calendars {

	final IUser user;

	/*
	 * Rep exposure: localCalendar.
	 */
	final ICalendar localCalendar;

	/*
	 * Index of the calendar for faster contains()
	 */
	final HashSet<Uid> localEventUids = new HashSet<Uid>();

	public Calendars(String name) {
		user = new AppUser(name);
		CalendarStorage.addCalendar(name);
		localCalendar = CalendarStorage.getCalendar(name);
	}

	/**
	 * Adding a remote user; for free time viewing function;
	 * 
	 * @param name
	 */
	public void AddRemoteUser(String name) {
		if (name == user.getName())
			throw new IllegalArgumentException();
		CalendarStorage.getCalendar(name);
	}

	public IUser getUser() {
		return user;
	}

	// needs to deprecate this
	/**
	 * Accessor to the main calendar of the user; rep exposure; 
	 * 
	 * @return
	 */
	public ICalendar getLocalCalendar() {
		return localCalendar;
	}

	public String toString() {
		return localCalendar.toString() + "\n" + CalendarStorage.toStaticString();
	}

	/**
	 * Ensures that an index of the calendar and its contents match
	 */
	private void checkRep() {
		
		assertTrue(localEventUids.size() == size());
		
		//localCalendar.getEvents().stream().map(VEvent::getUid).forEach(u -> localEventUids.contains(u));
	}

	/**
	 * Tests that a calendar contains a specific event
	 * 
	 * @return
	 */
	public boolean containsEvent(VEvent event) {
		return localEventUids.contains(event.getUid());
	}

	/**
	 * Adding a deep copy of VEvent into local calendar; defensive copying. returns
	 * false if an event exists.
	 * 
	 * @param event
	 */
	public boolean addEvent(VEvent event) {

		if (containsEvent(event))
			return false;

		localCalendar.addEvent(new VEvent(event));

		localEventUids.add(event.getUid());

		return true;
	}

	/**
	 * Returning a deep copy of VEvent with provided uid local calendar; defensive
	 * copying.
	 * 
	 * @param uid
	 * @return
	 */
	public VEvent readEvent(Uid uid) {

		List<VEvent> events = localCalendar.getEvents();

		VEvent event = null;

		for (VEvent ve : events) {
			if (ve.getUid().equals(uid)) {
				event = new VEvent(ve);
			}
		}
		return event;
	}

	/**
	 * Updates an event inside the calendar; 
	 * @param event
	 * @return
	 */
	public boolean updateEvent(VEvent event) {
		
		if (!containsEvent(event))
			return false;
			
		localCalendar.setComponent(event);
	
		return true;
		
	}
	
	/**
	 * Deletes an event from the calendar; 
	 * @param event
	 * @return
	 */
	public boolean deleteEvent(VEvent event) {

		if (!containsEvent(event))
			return false;

		localCalendar.removeComponent(event);

		localEventUids.remove(event.getUid());

		return true;

	}

	/**
	 * Returns size of events list;
	 * 
	 * @return
	 */
	public int size() {
		return localCalendar.getEvents().size();
	}
}
