package appointer.util;

import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IAppointmentEvent;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import appointer.net.client.appointments.RESTClient_Step_2_GetPending;
import appointer.net.client.appointments.RESTClient_Step_3_PostChanges;
import appointer.net.client.appointments.RESTClient_Step_4_GetResults;
import appointer.net.client.appointments.RESTClient_Step_5_PostComplete;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;

/**
 * Class with reusable test routines; is not a test itself
 *
 */
public class TestUtil {

	/**
	 * Creates demo appointment based on VEvent and returns its UID
	 * 
	 * @param attendeeEvent
	 *            must include Uid, start and end time, organizer and attendee
	 * @param OrganizerCalendars
	 * @param AttendeeCalendars
	 * @return
	 * @throws URISyntaxException
	 */
	public static UUID createAppointmentTest(IAppointmentEvent attendeeEvent, ICalendars OrganizerCalendars,
			ICalendars AttendeeCalendars) throws URISyntaxException {

		// assumed organizer name matches between

		HttpStatus statusOne = registerEvent(attendeeEvent);

		IAppointmentDTO appAnswerOrganizer = respondEvent(OrganizerCalendars);

		HttpStatus statusThree = reportEvent(appAnswerOrganizer);

		IAppointmentDTO appRequestAttendee = resultsEvent(attendeeEvent, AttendeeCalendars);

		HttpStatus statusFive = completeEvent(appRequestAttendee);

		return UUID.fromString(appRequestAttendee.getEventId());

	}

	public static HttpStatus registerEvent(IAppointmentEvent attendeeEvent) throws URISyntaxException {
		// Attendee creates appointment and packs it into a dto with unique UID;
		IAppointmentDTO appRequestAttendee = DTOAdapter.toAppointmentDTO(RequestType.CREATE, attendeeEvent);

		HttpStatus res = null;
		// Attendee sends a new appointment request
		res = RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		res = RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		return res;
	}

	public static IAppointmentDTO respondEvent(ICalendars OrganizerCalendars) throws URISyntaxException {
		IAppointmentDTO appAnswerOrganizer;

		final String organizerName = OrganizerCalendars.getUser().getName();

		// Organizer receives appointment to create
		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.CREATE, organizerName);

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.CREATE, organizerName);

		// organizer adds event to Calendar;
		IAppointmentEvent organiserEvent = DTOAdapter.toAppointmentEvent(appAnswerOrganizer);

		OrganizerCalendars.putEvent(organiserEvent);

		appAnswerOrganizer.setResponded(true);

		return appAnswerOrganizer;
	}

	public static HttpStatus reportEvent(IAppointmentDTO appAnswerOrganizer) throws URISyntaxException {

		HttpStatus res = null;

		// Organizer reports events
		res = RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		res = RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		return res;
	}

	public static IAppointmentDTO resultsEvent(IAppointmentEvent attendeeEvent, ICalendars AttendeeCalendars)
			throws URISyntaxException {

		final String organizerName = attendeeEvent.getOrganizer();

		IAppointmentDTO appRequestAttendee;

		// Attendee now must see Responded as True;
		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(organizerName,
				attendeeEvent.getUid(), attendeeEvent.getSequence());

		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(organizerName,
				attendeeEvent.getUid(), attendeeEvent.getSequence());

		AttendeeCalendars.putEvent(DTOAdapter.toAppointmentEvent(appRequestAttendee)); //

		appRequestAttendee.setComplete(true);
		return appRequestAttendee;
	}

	public static HttpStatus completeEvent(IAppointmentDTO appRequestAttendee) throws URISyntaxException {

		HttpStatus res = null;

		res = RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		res = RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		return res;
	}
	
//
//	/**
//	 * Updates existing appointment based on changes in VEvent
//	 * 
//	 * @param attendeeEvent
//	 *            must exist in both Calendars (search by Uid)
//	 * @param OrganizerCalendars
//	 *            must include attendeeEvent
//	 * @param AttendeeCalendars
//	 *            must include attendeeEvent
//	 * @throws URISyntaxException
//	 */
//	public static void updateAppointmentTest(IAppointmentEvent attendeeEvent, Calendars OrganizerCalendars,
//			ICalendars AttendeeCalendars) throws URISyntaxException {
//
//		IAppointmentDTO appRequestAttendee = registerEvent(attendeeEvent);
//
//		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);
//
//		IAppointmentDTO appAnswerOrganizer;
//
//		final String organizerName = appRequestAttendee.getOrganizer(); // assumed organizer name matches between
//																		// devices
//
//		// Organizer receives appointment to change
//		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
//				appRequestAttendee.getOrganizer());
//
//		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
//				appRequestAttendee.getOrganizer());
//
//		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
//				appRequestAttendee.getOrganizer());
//
//		// organizer adds event to Calendar;
//		UUID organiserEventID = UUID.fromString(appAnswerOrganizer.getEventId());
//
//		IAppointmentEvent organizerChangeEvent = OrganizerCalendars.getEvent(organiserEventID);
//
//		assertTrue(organizerChangeEvent != null); // assuming the eventtoChange was found in the Calendar of the
//													// organizer;
//		DTOAdapter.updateEvent(organizerChangeEvent, appAnswerOrganizer);
//
//		appAnswerOrganizer.setResponded(true);
//
//		reportEvent(appAnswerOrganizer);
//
//		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);
//
//		// Attendee now must see Responded as True;
//		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
//				appRequestAttendee.getRequestId());
//
//		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
//				appRequestAttendee.getRequestId());
//
//		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
//				appRequestAttendee.getRequestId());
//
//		appRequestAttendee.setComplete(true);
//
//		completeEvent(appRequestAttendee);
//
//		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);
//
//	}

}
