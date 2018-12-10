package appointer.util.io.console;

import java.util.List;
import java.util.Map.Entry;

import biweekly.component.ICalComponent;
import biweekly.component.VEvent;
import biweekly.property.ICalProperty;

public class CalendarPrinter {
	

	/**
	 * Prints properties of BiWeekly calendar component;
	 * 
	 * @param vEventOne
	 */
	public static <T extends ICalComponent> void printComponentProperties(T vEventOne) {
		for (Entry<Class<? extends ICalProperty>, List<ICalProperty>> property : vEventOne.getProperties()) {
			System.out.println(property.getKey().toString());
			property.getValue().forEach(System.out::println);
			property.getValue().forEach(p -> p.getParameters().forEach(System.out::println));
		}
	}

	/**
	 * Prints extra components of BiWeekly calendar component;
	 * 
	 * @param vEventOne
	 */
	public static <T extends ICalComponent> void printComponentComponents(T vEventOne) {
		for (Entry<Class<? extends ICalComponent>, List<ICalComponent>> component : vEventOne.getComponents()) {
			System.out.println(component.getKey().toString());
		}
	}
	
	
	public static void printEventRecurrence(VEvent event) {
		System.out.println(event.getRecurrenceRule().toString());
	}
}
