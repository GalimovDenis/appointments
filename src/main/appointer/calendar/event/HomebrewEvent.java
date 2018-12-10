package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import lombok.EqualsAndHashCode;

/**
 * Homebrew event that doesn't carry VEvent inside; alternative IEvent implementation
 */
@EqualsAndHashCode
public final class HomebrewEvent implements IEvent {

	private UUID uid; 
	private LocalDateTime timestamp;
	private LocalDateTime start;
	private LocalDateTime end;
	private String organizer;
	private String attendee;
	private Recurrence recur; 
	private AppointmentStatus status;
	

	public HomebrewEvent() {
		final LocalDateTime now = LocalDateTime.now();
		timestamp = now.truncatedTo(ChronoUnit.SECONDS);
		start = now.truncatedTo(ChronoUnit.MINUTES);
		end = now.truncatedTo(ChronoUnit.MINUTES);
		
	}
	
//	public HomebrewEvent(VEvent event) {
//		//TODO: stub;		
//	}
	
	@Override
	public void setEventID(UUID uid) {
		this.uid = uid;
	}

	@Override
	public void setEventTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void setTimeStart(LocalDateTime fillTime) {
		this.start = fillTime; 
	}

	@Override
	public void setTimeEnd(LocalDateTime endTime) {
		this.end = endTime; 
	}

	@Override
	public void adjustEventTime(TemporalAmount offset) {
		start =  start.plus(offset);
		end = end.plus(offset);
	}

	@Override
	public void setEventRepeats(Frequency frequency) {
		recur = new Recurrence.Builder(frequency).build();
	}

	@Override
	public void setOrganizer(String organiserName) {
		this.organizer = organiserName;
	}

	@Override
	public void setAttendee(String attendeeName) {
		
		this.attendee = attendeeName;
		
	}
	
	@Override
	public LocalDateTime getDateTimeStart() {
		return start; //immutable
	}

	@Override
	public LocalDateTime getDateTimeEnd() {
		return end; //immutable
	}

	@Override
	public String getOrganizer() {
		return organizer; //immutable
	}

	@Override
	public String getAttendee() {
		return attendee; //immutable
	}

	@Override
	public UUID getUid() {
		return uid; //immutable
	}

	@Override
	public LocalDateTime getDateTimeStamp() {
		return timestamp;
	}

	@Override
	public IDateRange createDateRange() {
		return new DateRange(start, end);
	}	

	@Override
	public String toString() {
		return "HomebrewEvent [uid=" + uid + ", timestamp=" + timestamp + ", start=" + start + ", end=" + end
				+ ", organizer=" + organizer + ", attendee=" + attendee + ", recur=" + recur + ", status=" + status
				+ "]";
	}

}
