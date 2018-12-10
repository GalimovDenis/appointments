package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import lombok.EqualsAndHashCode;

/**
 * Default IEvent implementation, carries VEvent;
 *
 */
@EqualsAndHashCode
public class ControlledEvent implements IEvent {

	private final VEvent event;

	public ControlledEvent() {
		event = new VEvent(); // timestamp automatically not null! 
		final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		setTimeStart(now);
		setTimeEnd(now);
	}
	
	public ControlledEvent(VEvent event) {
		this.event = new VEvent(event);
	}
	
	@Override
	public void setEventID(UUID uid) {
		this.event.setUid(uid.toString());
	}

	@Override
	public void setEventTimestamp(LocalDateTime timestamp) {
		event.setDateTimeStamp(DateAdapter.asDate(timestamp));
	}

	@Override
	public void setTimeStart(LocalDateTime fillTime) {
		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);
	}

	@Override
	public void setTimeEnd(LocalDateTime endTime) {
		Date end = DateAdapter.asDate(endTime);
		event.setDateEnd(end);
	}

	@Override
	public void adjustEventTime(TemporalAmount offset) {
		LocalDateTime localDateStart = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		LocalDateTime localDateEnd = DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
		event.setDateStart(DateAdapter.asDate(localDateStart.plus(offset)));
		event.setDateEnd(DateAdapter.asDate(localDateEnd.plus(offset)));
	}

	@Override
	public void setEventRepeats(Frequency frequency) {
		Recurrence recur = new Recurrence.Builder(frequency).build();
		event.setRecurrenceRule(recur);
	}

	@Override
	public void setOrganizer(String organiserName) {
		event.setOrganizer(new Organizer(organiserName, ""));
	}

	@Override
	public void setAttendee(String attendeeName) {
		
		event.getAttendees().clear();
		
		event.addAttendee(new Attendee(attendeeName, ""));
		
	}
	
	@Override
	public LocalDateTime getDateTimeStart() {
		return DateAdapter.asLocalDateTime(event.getDateStart().getValue());
	}

	@Override
	public LocalDateTime getDateTimeEnd() {
		return DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
	}

	@Override
	public String getOrganizer() {
		
		Organizer organizer = event.getOrganizer();
		
		if(organizer!=null) return organizer.getCommonName();
		
		return null; //mb empty string?
	}

	@Override
	public String getAttendee() {
		
		List<Attendee> attendees = event.getAttendees();
		
		switch(attendees.size()) {
		case 0:
			return null;
		case 1:
			return attendees.get(0).getCommonName();
		default: 
			assert false; //either 0 or 1 attendee;
		}

		return null;
		
	}

	@Override
	public UUID getUid() {
		return UUID.fromString(event.getUid().getValue());
	}

	@Override
	public LocalDateTime getDateTimeStamp() {
		return DateAdapter.asLocalDateTime(event.getDateTimeStamp().getValue());
	}

	@Override
	public IDateRange createDateRange() {
		return new DateRange(getDateTimeStart(), getDateTimeEnd());
	}	

	@Override
	public String toString() {
		return event.toString();
	}
	


}
