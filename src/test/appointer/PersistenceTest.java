package appointer;

import java.io.IOException;
import java.util.ArrayList;

import appointer.calendar.facades.EventFacade;
import appointer.util.io.disk.IOCalendarPersistence;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Tests saving and reading; 
 */
public class PersistenceTest {

	public static void main(String[] args) {

		testCalendarPersistence();
		
	}
	/**
	 * Writes and reads a calendar object to file;
	 */
	private static void testCalendarPersistence() {
		ICalendar calendar = new ICalendar();

		VEvent vEventOne = EventFacade.createEventCurrentTime();
	//	EventFacade.addOneHourToEvent(vEventOne);
		calendar.addEvent(vEventOne);
		
		ArrayList<ICalendar> alc = new ArrayList<ICalendar>();
		alc.add(calendar);		
		try {
			IOCalendarPersistence.dumpToFile(alc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			IOCalendarPersistence.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
