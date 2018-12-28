package com.appointments.commands;

/**
 * Sentinel pattern: created to get rid out of nulls;
 */
public class CmdSpecialEmpty extends CmdSpecialLeaf implements IAppCommand {

	@Override
	public void execute() {
	}

	@Override
	public void undo() {
	}

}
