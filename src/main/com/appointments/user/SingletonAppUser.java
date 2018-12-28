package com.appointments.user;

public class SingletonAppUser {

	private static IUser user;

	/**
	 * Returns the user with default name, if no user was created before, otherwise
	 * returns current user.
	 * 
	 * @param name
	 * @return
	 */
	public static synchronized IUser lazyGet() {
		if (user == null) {
			user = new AppUser("Default Name");
		}
		return user;
	}

	/**
	 * Returns the user with given name, if no user was created before, otherwise
	 * returns current user.
	 * 
	 * @param name
	 * @return
	 */
	public static synchronized IUser lazyGet(String name) {
		if (user == null) {
			user = new AppUser(name);
		}
		return user;
	}

}
