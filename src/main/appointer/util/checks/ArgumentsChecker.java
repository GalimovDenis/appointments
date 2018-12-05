package appointer.util.checks;

import appointer.net.dto.IAppointmentDTO;
import biweekly.component.VEvent;

public class ArgumentsChecker {
	/**
	 * Checks that AppointmentDTO is valid
	 * @param appEvent you want to check
	 */
	public static void checkAppDTO(IAppointmentDTO appEvent) {

		if (appEvent == null)
			throw new IllegalArgumentException("AppointmentDTO is null");

		String attendee = appEvent.getAttendee();

		checkStringNotEmpty(attendee, "Attendee");

		String organizer = appEvent.getOrganizer();

		checkStringNotEmpty(organizer, "Organizer");
		
		String eventID = appEvent.getEventId();

		checkStringNotEmpty(eventID, "eventID");
	}

	/**
	 * Checks a string is not null
	 * @param string you want to check
	 * @param stringName the name will be used in the exception text
	 */
	public static void checkStringNotEmpty(String string, String stringName) {
			
		if(stringName == null) throw new IllegalArgumentException("Name of checked string is null, can't throw proper exception");
		
		if (string == null)
			throw new IllegalArgumentException(stringName + " is null");
		if (string == "")
			throw new IllegalArgumentException(stringName + " is empty");
		
	}

	/**
	 * Checks is an object is not null
	 * @param obj you want to check
	 * @param objectName the name will be used in the exception text
	 */
	public static <T extends Object> void checkNotNull(T obj, String objectName) {
		if(objectName == null) throw new IllegalArgumentException("Name of checked string is null, can't throw proper exception");
		if (obj == null)
			throw new IllegalArgumentException(objectName + " is null");
	}
	
	/**
	 * Should check that an event is prepared for creating into Appointment; 
	 * business invariants
	 */
	public static void checkEventOK(VEvent event) {
		//TODO: stub
	}
}
