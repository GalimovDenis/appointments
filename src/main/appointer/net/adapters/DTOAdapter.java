package appointer.net.adapters;

import java.time.LocalDateTime;

import com.appointments.util.daterange.DateRange;
import com.appointments.util.daterange.DateRangeEmpty;
import com.appointments.util.daterange.IDateRange;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentCreate;
import appointer.net.dto.AppointmentDelete;
import appointer.net.dto.AppointmentRead;
import appointer.net.dto.AppointmentUpdate;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.util.date.DateAdapter;
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
	
	
	public static AppointmentCreate toAppointmentCreation(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentCreate appEvent = DTOFactory.appointmentCreate(range);

		fillEvent(appEvent, event);
		
		return appEvent;

	}
	
	public static AppointmentUpdate toAppointmentUpdate(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentUpdate appEvent = DTOFactory.appointmentUpdate(range);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentRead toAppointmentRead(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentRead appEvent = DTOFactory.appointmentRead(range);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentDelete toAppointmentDelete(VEvent event) {

		IDateRange range = IDateRange.empty();
		
		AppointmentDelete appEvent = DTOFactory.appointmentDelete(range);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	

	public static VEvent toAppointmentEvent(AppointmentCreate appCreation) {

		VEvent event = new VEvent();

		EventFacade.setOrganiser(event, appCreation.getOrganizer());

		EventFacade.addAttendee(event, appCreation.getAttendee());

		EventFacade.setEventStart(event, appCreation.getDateRange().getStart());

		EventFacade.setEventID(event, new Uid(appCreation.getEventId()));

		return event;
	}





}