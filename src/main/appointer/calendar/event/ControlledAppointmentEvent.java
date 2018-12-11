package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

public class ControlledAppointmentEvent implements IAppointmentEvent {

	private final LocalDateTime timestamp;
	private final UUID uid; 
	private final LocalDateTime start;
	private final LocalDateTime end;
	private final String organizer;
	private final String attendee;
	private final Recurrence repeats; 
	private AppointmentStatus status;
	

	protected ControlledAppointmentEvent(LocalDateTime timestamp, UUID uid, LocalDateTime start, LocalDateTime end,
			String organizer, String attendee, Recurrence recur) {
		this.timestamp = timestamp;
		this.uid = uid;
		this.start = start;
		this.end = end;
		
		this.organizer = organizer;
		this.attendee = attendee;
		this.repeats = recur;
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
	public IDateRange getDateRange() {
		return new DateRange(start, end);
	}	

	@Override
	public String toString() {
		return "ControlledAppointmentEvent [uid=" + uid + ", timestamp=" + timestamp + ", start=" + start + ", end=" + end
				+ ", organizer=" + organizer + ", attendee=" + attendee + ", recur=" + repeats + ", status=" + status
				+ "]";
	}

	@Override
	public Recurrence getEventRepeats() {
		return repeats;
	}

	@Override
	public AppointmentStatus getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

}
