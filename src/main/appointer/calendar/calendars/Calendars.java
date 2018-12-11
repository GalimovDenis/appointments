package appointer.calendar.calendars;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import appointer.calendar.event.IAppointmentEvent;
import appointer.calendar.event.IBuilderEvent;
import appointer.calendar.repository.CalendarsRepository;
import appointer.calendar.repository.ICalendarsRepository;
import appointer.user.AppUser;
import appointer.user.IUser;
import appointer.util.date.DateAdapter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;

/**
 * Represents user, local calendar and all remote calendars; Responsible for
 * creating and approving events across the network of app users;
 */
public class Calendars implements ICalendars {

	private final ICalendarsRepository repo;
	private final IUser user;
	private final ICalendar localCalendar;
	private final HashSet<UUID> uids;

	protected Calendars(String name) {
		repo = new CalendarsRepository();
		repo.addCalendar(name);
		localCalendar = repo.getCalendar(name);
		uids = new HashSet<UUID>();
		user = new AppUser(name);
	}

	@Override
	public void AddRemoteUser(String name) {
		if (name == user.getName())
			throw new IllegalArgumentException();
		repo.getCalendar(name);
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public Set<UUID> getUids() {
		return Collections.unmodifiableSet(uids);
	}

	// @Override
	// public String toString() {
	// return localCalendar.toString() + "\n" + repo.toStaticString();
	// }

	@Override
	public IAppointmentEvent getEvent(UUID uid) {

		assertTrue(uids.contains(uid));

		List<VEvent> events = localCalendar.getEvents();

		VEvent event = null;

		for (VEvent ve : events) {

			if (UUID.fromString(ve.getUid().getValue()).equals(uid)) {
				event = ve;
			}
		}

	//	System.out.println(event);
		
		return IBuilderEvent.produce(event).buildAppointment(); // flywheel pattern here needed

	}

	@Override
	public boolean putEvent(IAppointmentEvent event) { 
		
		UUID uid = UUID.fromString(event.getUid().toString());
		if (uids.contains(uid))
			return false;
		uids.add(uid);

		VEvent vEvent = new VEvent();
		vEvent.setOrganizer(new Organizer(event.getOrganizer(), ""));
		vEvent.addAttendee(new Attendee(event.getAttendee(), ""));
		vEvent.setDateTimeStamp(DateAdapter.asDate(event.getDateTimeStamp()));
		vEvent.setDateStart(DateAdapter.asDate(event.getDateTimeStart()));
		vEvent.setDateEnd(DateAdapter.asDate(event.getDateTimeEnd()));
		vEvent.setUid(uid.toString());
		localCalendar.addEvent(vEvent);
		return true;

	}

	@Override
	public boolean deleteEvent(IAppointmentEvent event) {

		
		return deleteEvent(event.getUid()); // expensive
		
		
	}

	@Override
	public boolean deleteEvent(UUID uid) {

		if (!uids.contains(uid)) return false;
		
		uids.remove(uid);
		
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
	public boolean equalByEventSet(ICalendars other) {

		return getUids().equals(other.getUids());

	}

	// @SuppressWarnings("unused")
	private void checkrep() {
		assertTrue(uids.size() == localCalendar.getEvents().size());
	};

	@Override
	public String toString() {
		checkrep();
		return user.toString() + " Has " + uids.size() + " Events " + System.lineSeparator()
				+ Stream.of(localCalendar.getEvents()).map(e -> e.toString()).collect(Collectors.joining());
	}
}
