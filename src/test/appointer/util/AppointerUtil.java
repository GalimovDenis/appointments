package appointer.util;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.UUID;

import appointer.calendar.calendars.Calendars;
import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IEvent;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import appointer.net.client.appointments.RESTClient_Step_2_GetPending;
import appointer.net.client.appointments.RESTClient_Step_3_PostChanges;
import appointer.net.client.appointments.RESTClient_Step_4_GetResults;
import appointer.net.client.appointments.RESTClient_Step_5_PostComplete;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;

/**
 * Class with reusable test routines
 *
 */
public class AppointerUtil {

	/**
	 * Does compliant demo event;
	 * @param Attendee
	 * @param Organizer
	 * @return
	 */
	public static IEvent createDemoEvent(String Attendee, String Organizer) {

		IEvent event = IEvent.create();

		event.setAttendee(Attendee);

		event.setOrganizer(Organizer);

		event.setEventID(UUID.randomUUID());

		return event;

	}

	/**
	 * Creates demo appointment based on VEvent and returns its UID
	 * @param attendeeEvent must include Uid, start and end time, organizer and attendee
	 * @param OrganizerCalendars
	 * @param AttendeeCalendars
	 * @return
	 * @throws URISyntaxException
	 */
	public static UUID createAppointmentTest(IEvent attendeeEvent, ICalendars OrganizerCalendars,
			ICalendars AttendeeCalendars) throws URISyntaxException {

		// Attendee creates appointment and packs it into a dto with unique UID;
		IAppointmentDTO appRequestAttendee = DTOAdapter.toAppointmentDTO(RequestType.CREATE, attendeeEvent);

		// Attendee sends a new appointment request
		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		IAppointmentDTO appAnswerOrganizer;

		final String organizerName = appRequestAttendee.getOrganizer(); // assumed organizer name matches between
																		// devices
		// Organizer receives appointment to create
		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.CREATE, organizerName);

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.CREATE, organizerName);

		// organizer adds event to Calendar;
		IEvent organiserEvent = DTOAdapter.toAppointmentEvent(appAnswerOrganizer);

		OrganizerCalendars.putEvent(organiserEvent);

		appAnswerOrganizer.setResponded(true);

		// Organizer reports events
		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		// Attendee now must see Responded as True;
		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.CREATE, organizerName,
				appRequestAttendee.getRequestId());

		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.CREATE, organizerName,
				appRequestAttendee.getRequestId());

		AttendeeCalendars.putEvent(DTOAdapter.toAppointmentEvent(appRequestAttendee)); //

		appRequestAttendee.setComplete(true);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		return UUID.fromString(appRequestAttendee.getEventId());

	}

	/**
	 * Updates existing appointment based on changes in VEvent
	 * @param attendeeEvent must exist in both Calendars (search by Uid)
	 * @param OrganizerCalendars must include attendeeEvent 
	 * @param AttendeeCalendars must include attendeeEvent
	 * @throws URISyntaxException
	 */
	public static void updateAppointmentTest(IEvent attendeeEvent, Calendars OrganizerCalendars,
			ICalendars AttendeeCalendars) throws URISyntaxException {

		IAppointmentDTO appRequestAttendee = DTOAdapter.toAppointmentDTO(RequestType.CREATE, attendeeEvent);

		// Attendee sends a change appointment request
		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequest(appRequestAttendee);

		IAppointmentDTO appAnswerOrganizer;

		final String organizerName = appRequestAttendee.getOrganizer(); // assumed organizer name matches between
																		// devices

		// Organizer receives appointment to change
		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
				appRequestAttendee.getOrganizer());

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
				appRequestAttendee.getOrganizer());

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.UPDATE,
				appRequestAttendee.getOrganizer());

		// organizer adds event to Calendar;
		UUID organiserEventID = UUID.fromString(appAnswerOrganizer.getEventId());

		IEvent organizerChangeEvent = OrganizerCalendars.getEvent(organiserEventID);

		assertTrue(organizerChangeEvent != null); // assuming the eventtoChange was found in the Calendar of the
													// organizer;
		DTOAdapter.updateEvent(organizerChangeEvent, appAnswerOrganizer);

		appAnswerOrganizer.setResponded(true);

		// Organizer reports events
		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		// Attendee now must see Responded as True;
		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
				appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
				appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.UPDATE, organizerName,
				appRequestAttendee.getRequestId());

		appRequestAttendee.setComplete(true);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

	}

}
