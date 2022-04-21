package codes.fepi.logic;

import codes.fepi.entity.DataStore;
import codes.fepi.entity.Show;
import codes.fepi.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StorageFactory {

	private StorageFactory() {
	}

	public static DataStore createDataStore() {
		// map copies
		final Map<String, List<Show>> shows = Repository.INSTANCE.getAllShows();
		final Map<String, User> users = UserManager.INSTANCE.getUsers();

		// remove users without shows and vice-versa
		shows.entrySet().removeIf(entry -> !users.containsKey(entry.getKey()) || entry.getValue().isEmpty());
		users.entrySet().removeIf(entry -> !shows.containsKey(entry.getKey()));
		return new DataStore(new ArrayList<>(users.values()), shows);
	}
}
