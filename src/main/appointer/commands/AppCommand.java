package appointer.commands;

/**
 * Commands for the calendar;
 * uses Composite pattern;
 * Each command stores the event it was applied on;
 * Each command stores the ICalendarsLocal object;
 */
public interface AppCommand {
	
	public void execute();

	public void undo();

	public void add(AppCommand appCommand);

	public void remove(AppCommand appCommand);

	public AppCommand getChild(int i);

}
