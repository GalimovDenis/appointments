package appointer.util.date.range;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable dateRange, safe from setting end before start
 * Can be serialized
 */
public class DateRange implements IDateRange {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime start;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime end;


	@JsonCreator
	public DateRange(@JsonProperty("start") LocalDateTime start, @JsonProperty("end") LocalDateTime end) {
		   if(start.isAfter(end)) throw new IllegalArgumentException("End date must be after the start date");
		   this.start = start;
		   this.end = end;
    }

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public LocalDateTime getStart() {
		return start;
	}

	@Override
	public LocalDateTime getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "start=" + start + ", end=" + end;
	}

}
