package appointer;

import java.net.URISyntaxException;

import appointer.calendar.facades.EventFacade;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.CreateRESTClient;
import appointer.net.client.ReportRESTClient;
import appointer.net.dto.AppointmentCreation;
import appointer.user.AppUser;
import appointer.user.IUser;
import biweekly.component.VEvent;

/**
 * Tests the connection from attendee to the server when we need to create event
 */
public class CreateAppointmentTest {

	/**
	 * Testing event creation. Requests are repeated to test their idempotency. 
	 * @param args
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws URISyntaxException {
		//ADDED MORE COMMENTS
		
		//Attendee creates appointemnt and packs it into a dto with unique UID; 
		VEvent testEvent = createDemoEvent();
		
		AppointmentCreation appCreation = DTOAdapter.toAppointmentCreation(testEvent);
		
		//Attendee sends a new appointment request
		CreateRESTClient.attendeeNewAppointment(appCreation);
		
		CreateRESTClient.attendeeNewAppointment(appCreation);
		
		CreateRESTClient.attendeeNewAppointment(appCreation);
		
		//Organiser receives appointment to create
		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());
		
		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());
		
		CreateRESTClient.organiserPendingAppoinmentPrint(appCreation.getOrganizer());
		
		// organiser adds event to Calendar;
		appCreation.setCreated(true);
		
		//Organiser reports events
		ReportRESTClient.organiserReportEvent(appCreation);
		
		ReportRESTClient.organiserReportEvent(appCreation);
		
		ReportRESTClient.organiserReportEvent(appCreation);
		
		// Attendee now must see Created as True; 
		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());
		
		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());
		
		ReportRESTClient.attendeeReportEvent(appCreation.getOrganizer(), appCreation.getUid());
		
	}

	private static VEvent createDemoEvent() {

		VEvent event = EventFacade.createEventCurrentTime();

		IUser user = new AppUser("Jane_Smith");

		EventFacade.addAttendee(event, user.getName());

		EventFacade.setOrganiser(event, "Ben_Bitdiddle");

		return event;

	}
}
