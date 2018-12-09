package appointer;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import appointer.calendar.facades.IEvent;

public class EventEqualsTest {
	
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static LocalDateTime timeNow= LocalDateTime.now();

	final static LocalDateTime timeStart = LocalDateTime.of(2018, Month.DECEMBER, 31, 19, 0, 0);
	final static LocalDateTime timeFinish = LocalDateTime.of(2018, Month.DECEMBER, 31, 20, 0, 0);
	final static UUID uid = UUID.randomUUID();
	
	/**
	 * Demonstrates steps to undertake in order to have events pass Equal tests; 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		IEvent eventA = IEvent.create();
		Thread.sleep(500); //If events have different timestamp, they are not equal. 
		IEvent eventB = IEvent.create();
		
		//therefore we set timestamp as of now;
		eventA.setEventTimestamp(timeNow);
		eventB.setEventTimestamp(timeNow);
		
		
		eventA.setEventID(uid);
		eventB.setEventID(uid);
		
		eventA.setOrganizer(Organizer);
		eventB.setOrganizer(Organizer);
		
		eventA.setAttendee(Attendee);
		eventB.setAttendee(Attendee);
		
		eventA.setTimeStart(timeStart);
		eventB.setTimeStart(timeStart);

		eventA.setTimeEnd(timeFinish);
		eventB.setTimeEnd(timeFinish);
		
		System.out.println(eventA);
		System.out.println(eventB);
		System.out.println(eventA.equals(eventB));
	}
}
