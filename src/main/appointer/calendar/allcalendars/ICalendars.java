package appointer.calendar.allcalendars;

import java.util.Set;
import java.util.UUID;

import appointer.calendar.facades.IEvent;
import appointer.user.IUser;

public interface ICalendars {

	public void AddRemoteUser(String name);
		
	public IUser getUser();

	public 	String toString();
	
	static IEvent newEvent() {
		return IEvent.create();
	}
	
	public IEvent getEvent(UUID uid);
	
	public boolean putEvent(IEvent event);
	
	public boolean deleteEvent(IEvent event);
	
	public boolean deleteEvent(UUID uid);

	public Set<UUID> getUids();

	boolean eventEquals(ICalendars other);
}