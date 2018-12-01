package appointer;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import appointer.calendar.allcalendars.Calendars;
import appointer.calendar.facades.EventFacade;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.CreateRESTClient;
import appointer.net.client.ReportRESTClient;
import appointer.net.dto.IAppointmentDTO;
import appointer.util.io.console.CalendarPrinter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

/**
 * Tests the connection from attendee to the server when we need to create event
 */
public class CRUDAppointmentTest {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organiser = "Ben_Bitdiddle";
	final static Calendars AttendeeCalendars = new Calendars(Attendee);
	final static Calendars OrganiserCalendars = new Calendars(Organiser);
	/**
	 * Testing event creation. Requests are repeated to test their idempotency. `
	 * 
	 * @param args
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws URISyntaxException {

	
		VEvent event = createDemoEvent(Attendee,Organiser);

		Uid uid = createAppointmentTest(event);
		
		CalendarPrinter.printCalendar(AttendeeCalendars.getLocalCalendar());

		CalendarPrinter.printCalendar(OrganiserCalendars.getLocalCalendar());
		
		List<VEvent> events = AttendeeCalendars.getLocalCalendar().getEvents();
		
		for(VEvent ve : events ) {
			if (ve.getUid() == uid) event = ve;
		}
		
		updateAppointmentTest(event);

	}

	private static void updateAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		EventFacade.moveEventStart(attendeeEvent, Duration.ofHours(24));
	
		//AppointmentUpdate appUpd = DTOAdapter.toAppointmentUpdate(attendeeEvent);
		
		// UpdateRESTClient.attendeeUpdateAppointment;

	}

	private static Uid createAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		// Attendee creates appointment and packs it into a dto with unique UID;
		IAppointmentDTO appCreationAttendee = DTOAdapter.toAppointmentCreation(attendeeEvent);

		System.out.println(appCreationAttendee);
		
		// Attendee sends a new appointment request
		CreateRESTClient.attendeeNewAppointment(appCreationAttendee);

		CreateRESTClient.attendeeNewAppointment(appCreationAttendee);

		CreateRESTClient.attendeeNewAppointment(appCreationAttendee);
		
		IAppointmentDTO appCreationOrganizer;
		
		// Organizer receives appointment to create
		appCreationOrganizer = CreateRESTClient.organizerPendingApproval(appCreationAttendee.getOrganizer());

		appCreationOrganizer = CreateRESTClient.organizerPendingApproval(appCreationAttendee.getOrganizer());

		appCreationOrganizer = CreateRESTClient.organizerPendingApproval(appCreationAttendee.getOrganizer());

		// organizer adds event to Calendar;	
		VEvent organiserEvent = DTOAdapter.toAppointmentEvent(appCreationOrganizer);
		
		OrganiserCalendars.getLocalCalendar().addEvent(organiserEvent);
		
		appCreationOrganizer.setResponded(true);

		// Organizer reports events
		ReportRESTClient.organizerReportEvent(appCreationOrganizer);

		ReportRESTClient.organizerReportEvent(appCreationOrganizer);

		ReportRESTClient.organizerReportEvent(appCreationOrganizer);

		// Attendee now must see Responded as True;
		ReportRESTClient.attendeeReportEvent(appCreationAttendee.getOrganizer(), appCreationAttendee.getRequestId());

		ReportRESTClient.attendeeReportEvent(appCreationAttendee.getOrganizer(), appCreationAttendee.getRequestId());

		ReportRESTClient.attendeeReportEvent(appCreationAttendee.getOrganizer(), appCreationAttendee.getRequestId());

		AttendeeCalendars.getLocalCalendar().addEvent(DTOAdapter.toAppointmentEvent(appCreationAttendee)); //

		return new Uid(appCreationAttendee.getEventId());
	}
	
	private static VEvent createDemoEvent(String Attendee, String Organiser) {

		VEvent event = EventFacade.createEventCurrentTime();

		EventFacade.addAttendee(event, Attendee);
	
		EventFacade.setOrganiser(event, Organiser);
		
		EventFacade.setEventID(event, new Uid(UUID.randomUUID().toString()));
		
		return event;

	}
}
