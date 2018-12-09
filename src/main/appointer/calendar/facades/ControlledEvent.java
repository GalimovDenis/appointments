package appointer.calendar.facades;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ControlledEvent implements IEvent {

	private final VEvent event;



	public ControlledEvent() {
		event = new VEvent();
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
	public Stream<LocalDateTime> getLocalDateStream() {
		DateIterator dates = event.getDateIterator(TimeZone.getDefault());
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(dates, Spliterator.ORDERED), false)
				.map(DateAdapter::asLocalDateTime);
	}

	@Override
	public LocalDateTime getDateStart() {
		return DateAdapter.asLocalDateTime(event.getDateStart().getValue());
	}

	@Override
	public LocalDateTime getDateEnd() {
		return DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
	}

	@Override
	public String getOrganizer() {
		return event.getOrganizer().getCommonName();
	}

	@Override
	public String getAttendee() {
		assertTrue(event.getAttendees().size() == 1); // one attendee for event max
		return event.getAttendees().get(0).getCommonName();
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
		return new DateRange(getDateStart(), getDateEnd());
	}	

	@Override
	public String toString() {
		return event.toString();
	}

}
