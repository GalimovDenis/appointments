package com.appointments.commands;

/**
 * Commands for the calendar;
 * Can composite several commands into one. 
 * Warning: when undo-redoing a chain of commands, you need to reverse the chain before redoing.
 */
public interface IAppCommand {
	
	public void execute();

	public void undo();

	public void add(IAppCommand appCommand);

	public void remove(IAppCommand appCommand);

	public IAppCommand getChild(int i);

}
