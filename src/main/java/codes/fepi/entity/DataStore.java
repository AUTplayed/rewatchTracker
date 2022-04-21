package codes.fepi.entity;

import java.util.List;
import java.util.Map;

public class DataStore {

	private List<User> users;
	private Map<String, List<Show>> shows;

	public DataStore() {
	}

	public DataStore(List<User> users, Map<String, List<Show>> shows) {
		this.users = users;
		this.shows = shows;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Map<String, List<Show>> getShows() {
		return shows;
	}

	public void setShows(Map<String, List<Show>> shows) {
		this.shows = shows;
	}
}
