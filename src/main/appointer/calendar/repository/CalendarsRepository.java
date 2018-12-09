package appointer.calendar.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import biweekly.ICalendar;

/**
 *  map for all ICalendars on the local system rep exposure; not
 * threadsafe;
 */
public class CalendarsRepository implements ICalendarsRepository {

	private  final Map<String, ICalendar> calendars = new HashMap<>();

	private  Map<String, ICalendar> getMap() {
		return calendars;
	}

	@Override
	public  String toStringAllCalendars() {
		return getMap().entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue())
				.collect(Collectors.joining());
	}

	// TODO: We need a method that prints only the calendars relevant to the name;
	@Override
	public  String toStringByName(String name) {
		return null;
	}

	/**
	 * Returns the local calendar for any application user, if it exists;
	 * can return null; 
	 */
	@Override
	public  ICalendar getCalendar(String userName) {
		return getMap().get(userName);
	}

	/**
	 * Returns the local calendar for any application user;
	 * will create calendar if it does not exist; 
	 */
	@Override
	public  ICalendar getOrCreateCalendar(String userName) {
		if (getCalendar(userName) == null) {
			addCalendar(userName);
		}
		return getCalendar(userName);
	}

	/**
	 */
	@Override
	public  boolean addCalendar(String userName) {
		ICalendar newCalendar = getMap().get(userName);
		if (newCalendar == null) {
			getMap().put(userName, new ICalendar());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public  boolean removeCalendar(String userName) {
		ICalendar oldCalendar = getMap().get(userName);
		if (oldCalendar != null) {
			getMap().remove(userName);
			return true;
		}
		return false;
	}

}
