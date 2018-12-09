package appointer.commands;

/**
 * Commands for the calendar;
 * Can composite several commands into one. 
 * Warning: when undo-redoing a chain of commands, you need to reverse the chain before redoing.
 */
public interface AppCommand {
	
	public void execute();

	public void undo();

	public void add(AppCommand appCommand);

	public void remove(AppCommand appCommand);

	public AppCommand getChild(int i);

}
