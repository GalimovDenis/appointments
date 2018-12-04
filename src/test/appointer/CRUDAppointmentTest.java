package appointer;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import appointer.calendar.allcalendars.Calendars;
import appointer.calendar.facades.EventFacade;
import appointer.net.adapters.DTOAdapter;
import appointer.net.appointclient.RESTClient_Step_5_PostComplete;
import appointer.net.appointclient.RESTClient_Step_1_PostNewRequest;
import appointer.net.appointclient.RESTClient_Step_2_GetPending;
import appointer.net.appointclient.RESTClient_Step_3_PostChanges;
import appointer.net.appointclient.RESTClient_Step_4_GetResults;
import appointer.net.dto.IAppointmentDTO;
import appointer.util.io.console.CalendarPrinter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

/**
 * CRUD operations tester
 */
public class CRUDAppointmentTest {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static Calendars AttendeeCalendars = new Calendars(Attendee);
	final static Calendars OrganizerCalendars = new Calendars(Organizer);

	/**
	 * Testing event operations. Requests are repeated to test their idempotency. `
	 * 
	 * @param args
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws URISyntaxException {

		VEvent eventToCreateI = createDemoEvent(Attendee, Organizer);

		Uid eventI_UID = createAppointmentTest(eventToCreateI);

		printAttendeAndOrganizerCalendars();//One event created

		VEvent eventToUpdate = findEventInAttendeeCalendar(eventI_UID);

		EventFacade.adjustEventTime(eventToUpdate, Duration.ofHours(24));

		updateAppointmentTest(eventToUpdate);

		printAttendeAndOrganizerCalendars();//One event moved 24 hours forward

		for (int i = 0; i < 10; i++) {

			createAppointmentTest(createDemoEvent(Attendee, Organizer));
			
		}
		
		printAttendeAndOrganizerCalendars();//Second event added

		printAttendeAndOrganizerCalendars();//Second event added

	}



	

	private static void updateAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		IAppointmentDTO appRequestAttendee = DTOAdapter.toAppointmentUpdate(attendeeEvent);

		// Attendee sends a change appointment request
		RESTClient_Step_1_PostNewRequest.attendeeRequestUpdate(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequestUpdate(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequestUpdate(appRequestAttendee);

		IAppointmentDTO appAnswerOrganizer;

		final String organizerName = appRequestAttendee.getOrganizer(); // assumed organizer name matches between
																		// devices

		// Organizer receives appointment to change
		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetUpdateRequest(appRequestAttendee.getOrganizer());

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetUpdateRequest(appRequestAttendee.getOrganizer());

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetUpdateRequest(appRequestAttendee.getOrganizer());

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
		RESTClient_Step_3_PostChanges.organizerReportUpdate(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReportUpdate(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReportUpdate(appAnswerOrganizer);

		// Attendee now must see Responded as True;
		RESTClient_Step_4_GetResults.attendeeReceiveUpdateReport(organizerName, appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveUpdateReport(organizerName, appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveUpdateReport(organizerName, appRequestAttendee.getRequestId());

		appRequestAttendee.setComplete(true);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

	}

	private static Uid createAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		// Attendee creates appointment and packs it into a dto with unique UID;
		IAppointmentDTO appRequestAttendee = DTOAdapter.toAppointmentCreation(attendeeEvent);

		// Attendee sends a new appointment request
		RESTClient_Step_1_PostNewRequest.attendeeRequestCreate(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequestCreate(appRequestAttendee);

		RESTClient_Step_1_PostNewRequest.attendeeRequestCreate(appRequestAttendee);

		IAppointmentDTO appAnswerOrganizer;

		final String organizerName = appRequestAttendee.getOrganizer(); // assumed organizer name matches between
																		// devices

		// Organizer receives appointment to create
		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetCreateRequest(organizerName);

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetCreateRequest(organizerName);

		appAnswerOrganizer = RESTClient_Step_2_GetPending.organizerGetCreateRequest(organizerName);

		// organizer adds event to Calendar;
		VEvent organiserEvent = DTOAdapter.toAppointmentEvent(appAnswerOrganizer);

		OrganizerCalendars.getLocalCalendar().addEvent(organiserEvent);

		appAnswerOrganizer.setResponded(true);

		// Organizer reports events
		RESTClient_Step_3_PostChanges.organizerReportCreate(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReportCreate(appAnswerOrganizer);

		RESTClient_Step_3_PostChanges.organizerReportCreate(appAnswerOrganizer);

		// Attendee now must see Responded as True;
		RESTClient_Step_4_GetResults.attendeeReceiveCreateReport(organizerName, appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveCreateReport(organizerName, appRequestAttendee.getRequestId());

		RESTClient_Step_4_GetResults.attendeeReceiveCreateReport(organizerName, appRequestAttendee.getRequestId());

		AttendeeCalendars.getLocalCalendar().addEvent(DTOAdapter.toAppointmentEvent(appRequestAttendee)); //

		appRequestAttendee.setComplete(true);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(appRequestAttendee);

		return new Uid(appRequestAttendee.getEventId());

	}

	private static VEvent createDemoEvent(String Attendee, String Organiser) {

		VEvent event = EventFacade.createEventCurrentTime();

		EventFacade.addAttendee(event, Attendee);

		EventFacade.setOrganiser(event, Organiser);

		EventFacade.setEventID(event, UUID.randomUUID().toString());

		System.out.println("Did event: " + event.getUid().getValue());

		return event;

		
	}
	
	private static VEvent findEventInAttendeeCalendar(Uid uid) {
		List<VEvent> events = AttendeeCalendars.getLocalCalendar().getEvents();

		VEvent event = null; 
		
		for (VEvent ve : events) {
			if (ve.getUid().equals(uid)) {
				event = ve;
			}
		}
		return event;
	}

	private static void printAttendeAndOrganizerCalendars() {
		System.out.println("Attendee " + Attendee);
		CalendarPrinter.printCalendar(AttendeeCalendars.getLocalCalendar());
		System.out.println("Organizer " + Organizer);
		CalendarPrinter.printCalendar(OrganizerCalendars.getLocalCalendar());
	}
}
