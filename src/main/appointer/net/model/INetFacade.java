package appointer.net.model;

import appointer.calendar.allcalendars.ICalendars;
import appointer.calendar.facades.IEvent;

public interface INetFacade {
	
	public boolean createAppointment(IEvent event, ICalendars attendeeCalendar, ICalendars organizerCalendar);
	
	public boolean readAppointment(IEvent event, ICalendars attendeeCalendar, ICalendars organizerCalendar);
	
	public boolean updateAppointment(IEvent event, ICalendars attendeeCalendar, ICalendars organizerCalendar);
	
	public boolean deleteAppointment(IEvent event, ICalendars attendeeCalendar, ICalendars organizerCalendar);
	
}
