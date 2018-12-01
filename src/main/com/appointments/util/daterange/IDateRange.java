package com.appointments.util.daterange;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DateRangeEmpty.class, name = "DateRangeEmpty"),
    @JsonSubTypes.Type(value = DateRange.class, name = "DateRange") }
)
public interface IDateRange {
	public boolean isEmpty();
	
	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String toString();
}
