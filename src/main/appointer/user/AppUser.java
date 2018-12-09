package appointer.user;

import appointer.net.client.util.NamesRESTClient;

/**
 * User class, final for correct equals()
 */
public final class AppUser implements IUser {


	private final String name;

	/**
	 * Creates a new user with the given name if the name is not taken;
	 * 
	 * @param name
	 *            user name
	 * @throws IllegalArgumentException
	 */
	public AppUser(String name) throws IllegalArgumentException {
		if (NamesRESTClient.isUnique(name)) {
			this.name = name;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "AppUser " + name;
	}

	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
