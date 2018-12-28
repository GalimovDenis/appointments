package com.appointments.net.adapters;

import java.time.LocalDateTime;
import java.util.UUID;

import com.appointments.calendar.event.IAppointmentEvent;
import com.appointments.calendar.event.IEventBuilder;
import com.appointments.net.dto.AppointmentDTO;
import com.appointments.net.dto.IAppointmentDTO;
import com.appointments.net.dto.RequestType;
import com.appointments.util.checks.ArgumentsChecker;
import com.appointments.util.date.range.IDateRange;

public class DTOAdapter {

	/**
	 * Setting mutable content onto event
	 * 
	 * @param appDTO
	 * @param event
	 * @return
	 */
	private static IAppointmentDTO updateDTOFromEvent(IAppointmentDTO appDTO, IAppointmentEvent event) {

		appDTO.setOrganizer(event.getOrganizer());

		appDTO.setAttendee(event.getAttendee());

		appDTO.setEventId(event.getUid().toString());

		appDTO.setTimestamp(event.getDateTimeStamp());
		
		return appDTO;
	}

	
	//TODO: IAppointmentEvent
	/**
	 * doing AppointmentDTO
	 * 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentDTO(RequestType type, IAppointmentEvent event) {

		ArgumentsChecker.checkNotNull(type, "RequestType");

		ArgumentsChecker.checkNotNull(event, "IAppointmentEvent");

		final IDateRange range = event.getDateRange();

		final AppointmentDTO appDTO = DTOFactory.createAppointmentDTO(event.getSequence(), range, type);

		updateDTOFromEvent(appDTO, event);

		return appDTO;

	}
	
	//refactor as toAppointmentDTO;
	/**
	 * updating an event based on the DTO;
	 * returns DTO with the timestamp of Event modifiacation; 
	 * @param eventToChange
	 * @param appDTO.se
	 */
	public static IAppointmentDTO updateEvent(IAppointmentEvent eventToChange, IAppointmentDTO appDTO) {
		
		IEventBuilder builderEvent = IEventBuilder.create();
		
		builderEvent.setOrganizer(appDTO.getOrganizer());

		builderEvent.setAttendee(appDTO.getAttendee());

		builderEvent.setTimeStart(appDTO.getDateRange().getStart());

		builderEvent.setTimeEnd(appDTO.getDateRange().getEnd());

		LocalDateTime timestamp = LocalDateTime.now();
		
		builderEvent.setEventTimestamp(timestamp);
		
		eventToChange = builderEvent.buildAppointment();
		
		appDTO.setTimestamp(timestamp);
		
		return appDTO;
	}

	//TODO: IAppointmentEvent
	/**
	 * Converting appointmentDTO into IEvent
	 * 
	 * @param appCreation
	 * @return
	 */
	public static IAppointmentEvent toAppointmentEvent(IAppointmentDTO appCreation) {

		final IEventBuilder builderEvent = IEventBuilder.create();
		
		builderEvent.setOrganizer(appCreation.getOrganizer());

		builderEvent.setAttendee(appCreation.getAttendee());

		builderEvent.setTimeStart(appCreation.getDateRange().getStart());

		builderEvent.setTimeEnd(appCreation.getDateRange().getEnd());

		builderEvent.setEventID(UUID.fromString(appCreation.getEventId()));

		//LocalDateTime timestamp = appCreation.getTimestamp();
		
		//controlledEvent.setEventTimestamp(timestamp != null ? timestamp : LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		
		builderEvent.setEventTimestamp(appCreation.getTimestamp());
				
		return builderEvent.buildAppointment();
	}



}