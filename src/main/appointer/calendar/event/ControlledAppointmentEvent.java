package appointer.calendar.event;

import java.time.LocalDateTime;
import java.util.UUID;

import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.util.Recurrence;

public class ControlledAppointmentEvent implements IAppointmentEvent {



	private final LocalDateTime timestamp;
	private final UUID uid; 
	private final LocalDateTime start;
	private final LocalDateTime end;
	private final String organizer;
	private final String attendee;
	// recurrence and status are not in equals, because they are null unsafe
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendee == null) ? 0 : attendee.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControlledAppointmentEvent other = (ControlledAppointmentEvent) obj;
		if (attendee == null) {
			if (other.attendee != null)
				return false;
		} else if (!attendee.equals(other.attendee))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (organizer == null) {
			if (other.organizer != null)
				return false;
		} else if (!organizer.equals(other.organizer))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}
