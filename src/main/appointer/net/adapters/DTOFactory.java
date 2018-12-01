package appointer.net.adapters;

import java.util.UUID;

import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.date.range.IDateRange;

public class DTOFactory {
		
	public static  AppointmentDTO createAppointmentDTO(IDateRange range, RequestType requestType) {
		return new AppointmentDTO(UUID.randomUUID(), range, requestType);
	}


}
