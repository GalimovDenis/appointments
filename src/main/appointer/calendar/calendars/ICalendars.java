package appointer.calendar.calendars;

import java.util.Set;
import java.util.UUID;

import appointer.calendar.event.IEvent;
import appointer.user.IUser;

/**
 * Holds the local calendar and the views for the remote calendars;
 */
public interface ICalendars {

	/**
	 * The remote user for querying free-busy 
	 * @param name
	 */
	public void AddRemoteUser(String name);
		
	/**
	 * The local user's name; gives us the phone holder
	 * @return
	 */
	public IUser getUser();

	public String toString();
	
	static IEvent newEvent() {
		return IEvent.create();
	}
	
	public boolean putEvent(IEvent event);
	
	public IEvent getEvent(UUID uid);
	
	public boolean deleteEvent(IEvent event);
	
	public boolean deleteEvent(UUID uid);

	public Set<UUID> getUids();

	/**
	 * Compares the sets of UUIDs between two Calendar classes;
	 * @param other implementation of ICalendars 
	 * @return true if both Calendars hold the same set of IEvent UUIDs.
	 */
	boolean equalByEventSet(ICalendars other);
}