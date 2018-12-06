package appointer.util;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import appointer.calendar.allcalendars.Calendars;
import appointer.calendar.facades.EventFacade;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import appointer.net.client.appointments.RESTClient_Step_2_GetPending;
import appointer.net.client.appointments.RESTClient_Step_3_PostChanges;
import appointer.net.client.appointments.RESTClient_Step_4_GetResults;
import appointer.net.client.appointments.RESTClient_Step_5_PostComplete;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import biweekly.component.VEvent;
import biweekly.property.Uid;

/**
 * Class with reusable test routines
 *
 */
public class TestUtil {

	/**
	 * Does compliant demo event;
	 * @param Attendee
	 * @param Organiser
	 * @return
	 */
	public static VEvent createDemoEvent(String Attendee, String Organiser) {

		VEvent event = EventFacade.createEventCurrentTime();

		EventFacade.addAttendee(event, Attendee);

		EventFacade.setOrganiser(event, Organiser);

		EventFacade.setEventID(event, UUID.randomUUID().toString());

//		System.out.println("Did event: " + event.getUid().getValue());

		return event;

	}

	//TODO: calls to ArgumentsChecker
	/**
	 * Creates demo appointment based on VEvent and returns its UID
	 * @param attendeeEvent must include Uid, start and end time, organizer and attendee
	 * @param OrganizerCalendars
	 * @param AttendeeCalendars
	 * @return
	 * @throws URISyntaxException
	 */
	public static Uid createAppointmentTest(VEvent attendeeEvent, Calendars OrganizerCalendars,
			Calendars AttendeeCalendars) throws URISyntaxException {

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
		VEvent organiserEvent = DTOAdapter.toAppointmentEvent(appAnswerOrganizer);

		OrganizerCalendars.getLocalCalendar().addEvent(organiserEvent);

		appAnswerOrganizer.setResponded(true);

		// Organizer reports events
		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReport(appAnswerOrganizer);

		// Attendee now must see Responded as True;
		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.CREATE, organizerName,
				appRequestAttendee.getRequestId());

		appRequestAttendee = RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.CREATE, organizerName,
				appRequestAttendee.getRequestId());

		AttendeeCalendars.getLocalCalendar().addEvent(DTOAdapter.toAppointmentEvent(appRequestAttendee)); //

		appRequestAttendee.setComplete(true);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		return new Uid(appRequestAttendee.getEventId());

	}

	/**
	 * Updates existing appointment based on changes in VEvent
	 * @param attendeeEvent must exist in both Calendars (search by Uid)
	 * @param OrganizerCalendars must include attendeeEvent 
	 * @param AttendeeCalendars must include attendeeEvent
	 * @throws URISyntaxException
	 */
	public static void updateAppointmentTest(VEvent attendeeEvent, Calendars OrganizerCalendars,
			Calendars AttendeeCalendars) throws URISyntaxException {

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
		String organiserEventID = appAnswerOrganizer.getEventId();

		List<VEvent> orgEvents = OrganizerCalendars.getLocalCalendar().getEvents();

		VEvent organizerChangeEvent = null;

		for (VEvent event : orgEvents) {

			if (event.getUid().getValue().equals(organiserEventID)) {

				organizerChangeEvent = event;
			}
		}
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
	
// TODO: proper refactoring please, not to toss 4 objects around
//	public static void printAttendeAndOrganizerCalendars() {
//		System.out.println("Attendee " + Attendee);
//		CalendarPrinter.printCalendar(AttendeeCalendars.getLocalCalendar());
//		System.out.println("Organizer " + Organizer);
//		CalendarPrinter.printCalendar(OrganizerCalendars.getLocalCalendar());
//	}

}
