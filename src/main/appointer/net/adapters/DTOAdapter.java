package appointer.net.adapters;

import java.time.LocalDateTime;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public class DTOAdapter {
	
	private static <T extends BaseAppointmentDTO> T fillEvent(T appDTO, VEvent event) {

		appDTO.setOrganizer(event.getOrganizer().getCommonName());

		appDTO.setAttendee(event.getAttendees().get(0).getCommonName());

		appDTO.setEventId(event.getUid().getValue());
		
		return appDTO;
	}
	
	private static IDateRange createDateRange(VEvent event) {
	
		LocalDateTime start = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		LocalDateTime end = DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
		return new DateRange(start, end);
		
	}
	
	
	public static AppointmentDTO toAppointmentCreation(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.CREATE);

		fillEvent(appEvent, event);
		
		return appEvent;

	}
	
	public static AppointmentDTO toAppointmentUpdate(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.UPDATE);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentDTO toAppointmentRead(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.READ);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentDTO toAppointmentDelete(VEvent event) {

		IDateRange range = IDateRange.empty();
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.DELETE);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	

	public static VEvent toAppointmentEvent(IAppointmentDTO appCreation) {

		VEvent event = new VEvent();

		EventFacade.setOrganiser(event, appCreation.getOrganizer());

		EventFacade.addAttendee(event, appCreation.getAttendee());

		EventFacade.setEventStart(event, appCreation.getDateRange().getStart());

		EventFacade.setEventID(event, new Uid(appCreation.getEventId()));

		return event;
	}





}