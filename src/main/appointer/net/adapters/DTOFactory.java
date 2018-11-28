package appointer.net.adapters;

import java.util.UUID;

import appointer.net.dto.AppointmentCreate;
import appointer.net.dto.AppointmentDelete;
import appointer.net.dto.AppointmentRead;
import appointer.net.dto.AppointmentUpdate;

public class DTOFactory {
		
	public static  AppointmentCreate appointmentCreate() {
		return new AppointmentCreate(UUID.randomUUID());
	}
	
	public static  AppointmentRead appointmentRead() {
		return new AppointmentRead(UUID.randomUUID());
	}
	
	public static  AppointmentUpdate appointmentUpdate() {
		return new AppointmentUpdate(UUID.randomUUID());
	}
	
	public static  AppointmentDelete appointmentDelete() {
		return new AppointmentDelete(UUID.randomUUID());
	}

}
