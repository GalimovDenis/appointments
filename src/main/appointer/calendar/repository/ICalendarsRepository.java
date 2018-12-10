package appointer.calendar.repository;

import biweekly.ICalendar;

/**
 * Holds a collection of ICalendars
 */
public interface ICalendarsRepository {

	public String toStringAllCalendars();

	// TODO: We need a method that prints only the calendars relevant to the name;
	public String toStringByName(String name);

	/**
	 * Returns the local calendar for any application user, if it exists; can return
	 * null;
	 */
	public ICalendar getCalendar(String userName);

	/**
	 * Returns the local calendar for any application user; will create calendar if
	 * it does not exist;
	 */
	public ICalendar getOrCreateCalendar(String userName);

	/**
	 */
	public boolean addCalendar(String userName);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	public boolean removeCalendar(String userName);
}