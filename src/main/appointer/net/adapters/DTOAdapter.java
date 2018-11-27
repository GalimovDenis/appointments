package appointer.net.adapters;

import java.util.UUID;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentCreation;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public class DTOAdapter {

	public static AppointmentCreation toAppointmentCreation(VEvent event) {

		AppointmentCreation appEvent = new AppointmentCreation();

		appEvent.setOrganizer(event.getOrganizer().getCommonName());

		appEvent.setAttendee(event.getAttendees().get(0).getCommonName());

		appEvent.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));

		appEvent.setEventID(event.getUid().getValue());
		
		// appEvent.setEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		// TODO: NPE ^
		UUID uid = UUID.randomUUID();
		
		appEvent.setUid(uid);

		return appEvent;

	}

	public static VEvent toAppointmentEvent(AppointmentCreation appCreation) {
		VEvent event = new VEvent();
		EventFacade.setOrganiser(event, appCreation.getOrganizer());
		EventFacade.addAttendee(event, appCreation.getAttendee());
		EventFacade.setEventStart(event, appCreation.getStart());
		EventFacade.setEventID(event, new Uid(appCreation.getEventID()));
		return event;
	}
}