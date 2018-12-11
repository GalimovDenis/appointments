package appointer.net.adapters;

import java.time.LocalDateTime;
import java.util.UUID;

import appointer.calendar.event.IBuilderEvent;
import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.checks.ArgumentsChecker;
import appointer.util.date.range.IDateRange;

public class DTOAdapter {

	/**
	 * Setting mutable content onto event
	 * 
	 * @param appDTO
	 * @param event
	 * @return
	 */
	private static <T extends BaseAppointmentDTO> T updateDTOFromEvent(T appDTO, IBuilderEvent event) {

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
	public static AppointmentDTO toAppointmentDTO(RequestType type, IBuilderEvent event) {

		ArgumentsChecker.checkNotNull(type, "RequestType");

		ArgumentsChecker.checkNotNull(event, "VEvent");

		final IDateRange range = event.getDateRange();

		final AppointmentDTO appDTO = DTOFactory.createAppointmentDTO(range, type);

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
	public static IAppointmentDTO updateEvent(IBuilderEvent eventToChange, IAppointmentDTO appDTO) {

		eventToChange.setOrganizer(appDTO.getOrganizer());

		eventToChange.setAttendee(appDTO.getAttendee());

		eventToChange.setTimeStart(appDTO.getDateRange().getStart());

		eventToChange.setTimeEnd(appDTO.getDateRange().getEnd());

		LocalDateTime timestamp = LocalDateTime.now();
		
		eventToChange.setEventTimestamp(timestamp);
		
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
	public static IBuilderEvent toAppointmentEvent(IAppointmentDTO appCreation) {

		final IBuilderEvent controlledEvent = IBuilderEvent.create();

		controlledEvent.setOrganizer(appCreation.getOrganizer());

		controlledEvent.setAttendee(appCreation.getAttendee());

		controlledEvent.setTimeStart(appCreation.getDateRange().getStart());

		controlledEvent.setTimeEnd(appCreation.getDateRange().getEnd());

		controlledEvent.setEventID(UUID.fromString(appCreation.getEventId()));

		//LocalDateTime timestamp = appCreation.getTimestamp();
		
		//controlledEvent.setEventTimestamp(timestamp != null ? timestamp : LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		
		controlledEvent.setEventTimestamp(appCreation.getTimestamp());
				
		return controlledEvent;
	}



}