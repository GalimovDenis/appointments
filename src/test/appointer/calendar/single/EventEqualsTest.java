package appointer.calendar.single;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import appointer.calendar.facades.EventFacade;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public class EventEqualsTest {
	
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static LocalDateTime timeNow= LocalDateTime.now();

	final static LocalDateTime timeStart = LocalDateTime.of(2018, Month.DECEMBER, 31, 19, 0, 0);
	final static LocalDateTime timeFinish = LocalDateTime.of(2018, Month.DECEMBER, 31, 20, 0, 0);
	final static Uid uid = new Uid(UUID.randomUUID().toString());
	
	/**
	 * Demonstrates steps to undertake in order to have events pass Equal tests; 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		VEvent eventA = new VEvent();
		Thread.sleep(500); //If events have different timestamp, they are not equal. 
		VEvent eventB = new VEvent();
		
		//therefore we set timestamp as of now;
		eventA.setDateTimeStamp(DateAdapter.asDate(timeNow));
		eventB.setDateTimeStamp(DateAdapter.asDate(timeNow));
		
		
		eventA.setUid(uid);
		eventB.setUid(uid);
		
		eventA.setOrganizer(Organizer);
		eventB.setOrganizer(Organizer);
		
		eventA.addAttendee(Attendee);
		eventB.addAttendee(Attendee);
		
		EventFacade.setEventStart(eventA, timeStart);
		EventFacade.setEventStart(eventB, timeStart);

		EventFacade.setEventStart(eventA, timeFinish);
		EventFacade.setEventStart(eventB, timeFinish);
		
		System.out.println(eventA);
		System.out.println(eventB);
		System.out.println(eventA.equals(eventB));
	}
}
