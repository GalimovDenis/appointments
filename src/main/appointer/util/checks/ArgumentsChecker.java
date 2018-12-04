package appointer.util.checks;

import appointer.net.dto.IAppointmentDTO;

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
	 * @param nameOfString the name will be used in the exception text
	 */
	public static void checkStringNotEmpty(String string, String nameOfString) {
			
		if(nameOfString == null) throw new IllegalArgumentException("Name of checked string is null, can't throw proper exception");
		
		if (string == null)
			throw new IllegalArgumentException(nameOfString + " is null");
		if (string == "")
			throw new IllegalArgumentException(nameOfString + " is empty");
		
	}
}
