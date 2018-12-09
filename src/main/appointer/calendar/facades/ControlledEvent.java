package appointer.calendar.facades;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TimeZone;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ControlledEvent implements EventFacade {

	VEvent event = new VEvent();

	@Override
	public void setEventID(String string) {
		this.event.setUid(string);
	}

	public ControlledEvent() {
		final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		setEventStart(now);
		setEventEnd(now);
	}

	@Override
	public void setEventTimestamp(LocalDateTime timestamp) {
		event.setDateTimeStamp(DateAdapter.asDate(timestamp));
	}

	@Override
	public void setEventStart(LocalDateTime fillTime) {
		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);
	}

	@Override
	public void setEventEnd(LocalDateTime endTime) {
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
	public void setOrganiser(String organiserName) {
		event.setOrganizer(new Organizer(organiserName, ""));
	}

	@Override
	public void setAttendee(String attendeeName) {
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
	public String getUid() {
		return event.getUid().getValue();
	}

	@Override
	public LocalDateTime getDateTimeStamp() {
		return DateAdapter.asLocalDateTime(event.getDateTimeStamp().getValue());
	}

}
