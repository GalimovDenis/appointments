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
import appointer.net.dto.AppointmentCreation;
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
		
		CalendarPrinter.printCalendar(AttendeeCalendars.getLocalCalenar());

		CalendarPrinter.printCalendar(OrganiserCalendars.getLocalCalenar());
		
		List<VEvent> events = AttendeeCalendars.getLocalCalenar().getEvents();
		
		for(VEvent ve : events ) {
			if (ve.getUid() == uid) event = ve;
		}
		
		updateAppointmentTest(event);

	}

	private static void updateAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		EventFacade.moveEventStart(attendeeEvent, Duration.ofHours(24));
	
		//AppointmentUpdate appUpd = DTOAdapter.toAppointmentUpdate(event);
		// UpdateRESTClient.attendeeUpdateAppointment;

	}

	private static Uid createAppointmentTest(VEvent attendeeEvent) throws URISyntaxException {

		// Attendee creates appointment and packs it into a dto with unique UID;
		AppointmentCreation appCreation = DTOAdapter.toAppointmentCreation(attendeeEvent);

		// Attendee sends a new appointment request
		CreateRESTClient.attendeeNewAppointment(appCreation);

		CreateRESTClient.attendeeNewAppointment(appCreation);

		CreateRESTClient.attendeeNewAppointment(appCreation);

		// Organiser receives appointment to create
		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());

		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());

		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());

		// organiser adds event to Calendar;	
		VEvent organiserEvent = DTOAdapter.toAppointmentEvent(appCreation);
		
		OrganiserCalendars.getLocalCalenar().addEvent(organiserEvent);
		
		appCreation.setCreated(true);


		// Organiser reports events
		ReportRESTClient.organiserReportEvent(appCreation);

		ReportRESTClient.organiserReportEvent(appCreation);

		ReportRESTClient.organiserReportEvent(appCreation);

		// Attendee now must see Created as True;
		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());

		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());

		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());

		AttendeeCalendars.getLocalCalenar().addEvent(DTOAdapter.toAppointmentEvent(appCreation)); //

		return new Uid(appCreation.getEventID());
	}
	
	private static VEvent createDemoEvent(String Attendee, String Organiser) {

		VEvent event = EventFacade.createEventCurrentTime();

		EventFacade.addAttendee(event, Attendee);
	
		EventFacade.setOrganiser(event, Organiser);
		
		EventFacade.setEventID(event, new Uid(UUID.randomUUID().toString()));
		
		return event;

	}
}
