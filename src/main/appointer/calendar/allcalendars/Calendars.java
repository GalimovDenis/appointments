package appointer.calendar.allcalendars;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import appointer.calendar.facades.ControlledEvent;
import appointer.calendar.facades.IEvent;
import appointer.user.AppUser;
import appointer.user.IUser;
import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Represents user, local calendar and all remote calendars; Responsible for
 * creating and approving events across the network of app users;
 */
public class Calendars implements ICalendars {

	private final IUser user;
	private final ICalendar localCalendar;
	private final HashSet<UUID> uids;
	
	public Calendars(String name) {
		user = new AppUser(name);
		CalendarStorage.addCalendar(name);
		localCalendar = CalendarStorage.getCalendar(name);
		uids = new HashSet<UUID>();
	}

	@Override
	public void AddRemoteUser(String name) {
		if (name == user.getName())
			throw new IllegalArgumentException();
		CalendarStorage.getCalendar(name);
	}

	
	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public Set<UUID> getUids() {
		return Collections.unmodifiableSet(uids);
	}
	
	@Override
	public String toString() {
		return localCalendar.toString() + "\n" + CalendarStorage.toStaticString();
	}

	@Override
	public IEvent getEvent(UUID uid) {

		assertTrue(uids.contains(uid));
		
		List<VEvent> events = localCalendar.getEvents();

		VEvent event = null;

		for (VEvent ve : events) {
			
			if (UUID.fromString(ve.getUid().getValue()).equals(uid)) {
				event = ve;
			}
		}
		return new ControlledEvent(event); // flywheel pattern here needed

	}

	@Override
	public boolean putEvent(IEvent event) { // unflexible due to NPE in getters; needs only appointment
		VEvent vEvent = new VEvent();
		vEvent.setOrganizer(event.getOrganizer());
		vEvent.addAttendee(event.getAttendee());
		vEvent.setDateTimeStamp(DateAdapter.asDate(event.getDateTimeStamp()));
		vEvent.setDateStart(DateAdapter.asDate(event.getDateStart()));
		vEvent.setDateEnd(DateAdapter.asDate(event.getDateEnd()));
		UUID uid = UUID.fromString(event.getUid().toString());
		uids.add(uid);
		vEvent.setUid(uid.toString());
		localCalendar.addEvent(vEvent);
		return true; 
	}

	
	@Override
	public boolean deleteEvent(IEvent event) {

		return deleteEvent(event.getUid()); // expensive
	}

	@Override
	public boolean deleteEvent(UUID uid) {
	
		assertTrue(uids.contains(uid));
		List<VEvent> events = localCalendar.getEvents();
		for (VEvent ve : events) {
			if (ve.getUid().getValue().equals(uid.toString())) {
				localCalendar.removeComponent(ve);
				return true;
			}
		}
		return false; 
	
	}

	@Override
	public boolean eventEquals(ICalendars other) {
		
		return getUids().equals(other.getUids());

	}
	
	@SuppressWarnings("unused")
	private void checkrep() {
		//TODO: rep check
	};
	
	public String toString(ICalendar calendar) {
		return user.toString() + System.lineSeparator() + Stream.of(localCalendar.getEvents())
				.map(e -> e.toString()).collect(Collectors.joining());
	}
}
