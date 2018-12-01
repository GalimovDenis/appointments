package appointer.net.adapters;

import java.util.UUID;

import com.appointments.util.daterange.IDateRange;

import appointer.net.dto.AppointmentCreate;
import appointer.net.dto.AppointmentDelete;
import appointer.net.dto.AppointmentRead;
import appointer.net.dto.AppointmentUpdate;

public class DTOFactory {
		
	public static  AppointmentCreate appointmentCreate(IDateRange range) {
		return new AppointmentCreate(UUID.randomUUID(), range );
	}
	
	public static  AppointmentRead appointmentRead(IDateRange range) {
		return new AppointmentRead(UUID.randomUUID(), range);
	}
	
	public static  AppointmentUpdate appointmentUpdate(IDateRange range) {
		return new AppointmentUpdate(UUID.randomUUID(), range);
	}
	
	public static  AppointmentDelete appointmentDelete(IDateRange range) {
		return new AppointmentDelete(UUID.randomUUID(), range);
	}

}
