package appointer.calendar.calendars;

import java.util.Set;
import java.util.UUID;

import appointer.calendar.event.IBuilderEvent;
import appointer.calendar.event.ITimeRangeEvent;
import appointer.user.IUser;

/**
 * Holds the local calendar and the views for the remote calendars;
 */
public interface ICalendars {

	/**
	 * Creates calendars for the given user name.
	 * @param userName
	 * @return
	 */
	public static Calendars create(String userName) {
		return new Calendars(userName);
	}

	/**
	 * The local user's name; gives us the phone holder
	 * @return
	 */
	public IUser getUser();

	/**
	 * The remote user for querying free-busy 
	 * @param name
	 */
	public void AddRemoteUser(String name);
		
	public String toString();

	/**
	 * Creates a new blank event.
	 */
	static IBuilderEvent newEvent() {
		return IBuilderEvent.create();
	}
	
	
	/*
	 * IBuilderEvent probably should not be allowed to Calendar, kept only as incomplete object
	 * Instead, stuff it with IAppointmentEvents and ITimeRangeEvents;
	 */
	/**
	 * @param event must be an appointment event (has organizer, attendee, timestamp, uid, date start, date end);
	 */
	public boolean putEvent(IBuilderEvent event); // nice preconditions. can we make a better type pls?
	
	public IBuilderEvent getEvent(UUID uid);
	
	public boolean deleteEvent(IBuilderEvent event);
	
	public boolean deleteEvent(UUID uid);

	/**
	 * Gets a set of UUIDs that corresponds to the stored Events. 
	 */
	public Set<UUID> getUids();

	/**
	 * Compares the sets of UUIDs between two Calendar classes;
	 * @param other implementation of ICalendars 
	 * @return true if both Calendars hold the same set of IEvent UUIDs.
	 */
	boolean equalByEventSet(ICalendars other);
}