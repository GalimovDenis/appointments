package appointer.net.adapters;

import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.date.range.IDateRange;

public class DTOFactory {
		
	public static  AppointmentDTO createAppointmentDTO(Integer id, IDateRange range, RequestType requestType) {
		return new AppointmentDTO(id, range, requestType);
	}


}
