package appointer.calendar.single;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import appointer.user.IUser;
import appointer.util.date.DateAdapter;
import appointer.util.io.console.CalendarPrinter;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.parameter.FreeBusyType;

/**
 * Holds a map of users and SingleCalendars;
 */
public class SingleCalendar implements ICalendarsLocal {
	/**
	 * Abstraction function: name, calendars -> ICalendar
	 */
	/**
	 * Rep invariant: user and calendars not null;
	 */
	private final IUser user;
	private static final Map<IUser, ICalendar> calendars = new HashMap<>();

	/**
	 * @param appUser
	 */
	public SingleCalendar(IUser appUser) {
		if (appUser == null)
			throw new IllegalArgumentException();
		user = appUser;
	}

	/**
	 * Returns the local calendar for any application user;
	 * 
	 * @param appUser
	 * @return
	 */
	public static ICalendar getLocalCalendar(IUser appUser) {
		if (calendars.get(appUser) == null) {
			calendars.put(appUser, new ICalendar());
		}
		return calendars.get(appUser);
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	/**
	 * Returns the local calendar for the default user
	 */
	public ICalendar getLocalCalendar() {
		return getLocalCalendar(user);
	}

	/**
	 * Reads time of start and end of event and then creates and adds to calendar an
	 * instance of VFreeBusy class;
	 * 
	 * @param calendar
	 * @param event
	 */
	// does too much, has to refactor;
	// event transparency exist;
	// works only in case of non-repeating event
	public void addBusy(ICalendar calendar, VEvent event) {
		VFreeBusy freebusy = new VFreeBusy();
		Date start = event.getDateStart().getValue();
		Date end = DateAdapter.asDate( // null pointer if no duration
				DateAdapter.asLocalDateTime(start).plus(event.getDuration().getValue().toMillis(), ChronoUnit.MILLIS));
		freebusy.addFreeBusy(FreeBusyType.BUSY, start, end);
		calendar.addFreeBusy(freebusy);
	}

	public boolean checkBusy(ICalendar calendar, LocalDateTime startTime, LocalDateTime endTime) {
		return false;
	}

	public boolean checkBusy(ICalendar calendar, LocalDateTime time) {
		return false;
	}

	@Override
	public String toString() {
		return "Calendar of " + user.getName() + "\n" + CalendarPrinter.ICalendarToString(this.getLocalCalendar());
	}

}
