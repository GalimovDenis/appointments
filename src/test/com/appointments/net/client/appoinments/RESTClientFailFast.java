package com.appointments.net.client.appoinments;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;

import com.appointments.calendar.calendars.ICalendars;
import com.appointments.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import com.appointments.net.client.appointments.RESTClient_Step_2_GetPending;
import com.appointments.net.client.appointments.RESTClient_Step_3_PostChanges;
import com.appointments.net.client.appointments.RESTClient_Step_4_GetResults;
import com.appointments.net.client.appointments.RESTClient_Step_5_PostComplete;
import com.appointments.net.dto.RequestType;

/**
 * ` Testing net.client.appointments for fail-fast behavior;
 */
public class RESTClientFailFast {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static ICalendars AttendeeCalendars = ICalendars.create(Attendee);
	final static ICalendars OrganizerCalendars = ICalendars.create(Organizer);

	@Test(expected = IllegalArgumentException.class)
	public void testINull() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequest(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIIINull() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReport(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVNull() throws URISyntaxException {
		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIIFirstArgNull() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(null, Organizer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIISecondArgNull() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.READ, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIISecondArgEmpty() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.READ, "");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testIVFirstArgNull() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport(null, UUID.randomUUID(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVFirstArgEmpty() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport("", UUID.randomUUID(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVSecondArgNull() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport("", null, 0);
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void testIVThirdArgNull() throws URISyntaxException {
//		RESTClient_Step_4_GetResults.attendeeReceiveReport(Organizer, UUID.randomUUID(), -100);
//	}


}
