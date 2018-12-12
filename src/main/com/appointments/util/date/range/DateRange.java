package com.appointments.util.date.range;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable dateRange, safe from setting end before start
 * Can be serialized
 */
public final class DateRange implements IDateRange {

	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		DateRange other = (DateRange) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

}
